package com.fhypayaso.orochi.service;

import com.fhypayaso.orochi.bean.Apk;
import com.fhypayaso.orochi.dao.ApkMapper;
import com.fhypayaso.orochi.model.exception.QueryException;
import com.fhypayaso.orochi.model.exception.UploadExecption;
import com.fhypayaso.orochi.model.request.ApkRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApkService {


    @Autowired
    ApkMapper mApkMapper;

    @Autowired
    UploadService mUploadService;

    @Transactional
    public void create(ApkRequest request) throws Exception {

        Apk apk = new Apk();
        apk.setAppId(request.getAppId());
        apk.setVersionCode(request.getVersionCode());
        apk.setDesc(request.getDesc());
        String downloadUrl = mUploadService.upload(request.getApkFile(), UploadService.APK);
        // apk上传不允许失败
        if (downloadUrl == null) {
            throw new UploadExecption();
        }
        apk.setDownloadUrl(downloadUrl);
        long curTime = System.currentTimeMillis();
        apk.setCreatedOn(curTime);
        apk.setUpdateOn(curTime);

        mApkMapper.insert(apk);
    }

    @Transactional
    public void update(ApkRequest request) throws Exception {
        Apk apk = mApkMapper.selectByPrimaryKey(request.getId());
        if (apk == null) {
            throw new QueryException("apk不存在");
        }
        // 版本号和下载地址都不能更改
        apk.setDesc(request.getDesc());
        apk.setUpdateOn(System.currentTimeMillis());
        mApkMapper.updateByPrimaryKey(apk);
    }

    public Apk query(int id) throws QueryException {
        Apk apk = mApkMapper.selectByPrimaryKey(id);
        if (apk == null) {
            throw new QueryException("apk不存在");
        }
        return apk;
    }

    public List<Apk> queryAll() {
        return mApkMapper.selectAll();
    }

    public List<String> queryAllUrlByApp(int appId) {
        return mApkMapper.selectAllUrlByApp(appId);
    }


    @Transactional
    public void delete(int id) throws QueryException {
        Apk apk = mApkMapper.selectByPrimaryKey(id);
        if (apk == null) {
            throw new QueryException();
        }
        mUploadService.delete(apk.getDownloadUrl());
        mApkMapper.deleteByPrimaryKey(id);
    }
}
