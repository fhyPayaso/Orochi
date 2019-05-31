package com.fhypayaso.orochi.service;

import com.fhypayaso.orochi.model.exception.FileTypeException;
import com.fhypayaso.orochi.utils.FileUtil;
import com.fhypayaso.orochi.utils.TextUtil;
import com.fhypayaso.orochi.utils.TimeUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class UploadService {

    public static final int IMG = 1001;
    public static final int APK = 1002;


    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.path}")
    private String path;


    public String upload(MultipartFile multipartFile, int type) throws FileTypeException, IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }

        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        String name = multipartFile.getOriginalFilename();
        switch (type) {
            case IMG:
                return uploadImage(inputStream, name);
            case APK:
                return uploadApk(inputStream, name);
            default:
                return null;
        }
    }

    private String uploadImage(FileInputStream inputStream, String name) throws FileTypeException {
        String type = FileUtil.getFileType(name);
        if (!type.equals("jpg") && !type.equals("jpeg") && !type.equals("png")) {
            throw new FileTypeException();
        }
        return uploadFile(inputStream, String.format(bucket, "pic"), String.format(path, "img"), gainFileKey(name));
    }

    private String uploadApk(FileInputStream inputStream, String name) throws FileTypeException {
        String type = FileUtil.getFileType(name);
        if (!type.equals("apk")) {
            throw new FileTypeException();
        }
        return uploadFile(inputStream, String.format(bucket, "apk"), String.format(path, "apk"), gainFileKey(name));
    }


    private String uploadFile(FileInputStream inputStream, String bucket, String baseUrl, String key) {

        //构造一个带指定Zone对象的配置类 zone1: 华北
        Configuration cfg = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, key, uploadToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return baseUrl + putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                ex2.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 删除文件
     *
     * @param url 文件外链
     */
    public void delete(String url) {

        if (TextUtil.isEmpty(url)) {
            return;
        }

        Configuration cfg = new Configuration(Zone.zone1());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        String key = praseFileKey(url);

        try {
            Response response = bucketManager.delete(String.format(bucket, url.contains("apk") ? "apk" : "pic"), key);
            if (response.statusCode == 200) {
                System.out.println(key + " delete success");
            } else {
                System.out.println(key + "===>>" + response.error);
            }
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    /**
     * 批量删除文件(用于批量删除apk)
     *
     * @param urlList 文件外链列表
     */
    public void deleteAll(List<String> urlList) {

        if (urlList == null || urlList.isEmpty()) {
            return;
        }

        Configuration cfg = new Configuration(Zone.zone1());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            //单次批量请求的文件数量不得超过1000
            String[] keyList = new String[urlList.size()];
            for (int i = 0; i < keyList.length; i++) {
                keyList[i] = praseFileKey(urlList.get(i));
            }
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(String.format(bucket, "apk"), keyList);
            Response response = bucketManager.batch(batchOperations);
            // 获取每一次删除的结果
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keyList.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keyList[i];
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.data.error);
                }
            }
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
    }


    /**
     * @param fileName 文件名
     * @return 文件key
     */
    private String gainFileKey(String fileName) {
        String subStr = FileUtil.getFileType(fileName);
        String curTime = TimeUtil.stampToDate(System.currentTimeMillis());
        return curTime + "." + subStr;
    }

    /**
     * @param url 下载外链
     * @return 文件key
     */
    private String praseFileKey(String url) {
        int index = url.lastIndexOf("/") + 1;
        return url.substring(index);
    }


}
