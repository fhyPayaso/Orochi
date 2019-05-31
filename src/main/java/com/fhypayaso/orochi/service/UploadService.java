package com.fhypayaso.orochi.service;

import com.fhypayaso.orochi.model.exception.FileTypeException;
import com.fhypayaso.orochi.utils.FileUtil;
import com.fhypayaso.orochi.utils.TimeUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service
public class UploadService {

    private static final String ACCESS_KEY = "V4bRlWyjq34V1c2WVvd39rm4WR7qAcaTtQ0Zs7rL";
    private static final String SECRET_KEY = "ot-QQmfoY_Cnnn9FZ2B2_-nNuakOIuQubvFOe1-J";

    private static final String BUCKET_IMG = "orochi-pic";
    private static final String BUCKET_APK = "orochi-apk";

    private static final String BASE_URL_IMG = "http://orochi.img.hellofhy.cn/";
    private static final String BASE_URL_APK = "http://orochi.apk.hellofhy.cn/";


    public String uploadImage(FileInputStream inputStream, String name) throws FileTypeException {
        String type = FileUtil.getFileType(name);
        if (!type.equals(".jpg") && !type.equals(".jpeg") && !type.equals(".png")) {
            throw new FileTypeException();
        }
        return uploadFile(inputStream, BUCKET_IMG, BASE_URL_IMG, getFileKey(name));
    }

    public String uploadApk(FileInputStream inputStream, String name) throws FileTypeException {
        String type = FileUtil.getFileType(name);
        if (!type.equals(".apk")) {
            throw new FileTypeException();
        }
        return uploadFile(inputStream, BUCKET_APK, BASE_URL_APK, getFileKey(name));
    }


    private String uploadFile(FileInputStream inputStream, String bucket, String baseUrl, String key) {

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
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


    private String getFileKey(String fileName) {
        String subStr = FileUtil.getFileType(fileName);
        String curTime = TimeUtil.stampToDate(System.currentTimeMillis());
        return curTime + subStr;
    }


}
