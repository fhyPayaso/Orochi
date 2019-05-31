package com.fhypayaso.orochi.service;

import com.fhypayaso.orochi.bean.Apk;
import com.fhypayaso.orochi.bean.App;
import com.fhypayaso.orochi.dao.ApkMapper;
import com.fhypayaso.orochi.dao.AppMapper;
import com.fhypayaso.orochi.model.exception.QueryException;
import com.fhypayaso.orochi.model.request.AppRequest;
import com.fhypayaso.orochi.utils.TextUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppService {

    @Autowired
    AppMapper mAppMapper;

    @Autowired
    ApkMapper mApkMapper;

    @Autowired
    UploadService mUploadService;


    @Transactional
    public void create(AppRequest request) throws Exception {

        App app = new App();
        app.setName(request.getName());
        app.setDesc(request.getDesc());
        String imgUrl = mUploadService.upload(request.getCover(), UploadService.IMG);
        if (imgUrl != null) {
            app.setCoverImageUrl(imgUrl);
        }
        long curTime = System.currentTimeMillis();
        app.setCreatedOn(curTime);
        app.setUpdateOn(curTime);

        mAppMapper.insert(app);
    }

    @Transactional
    public void update(AppRequest request) throws Exception {
        App app = mAppMapper.selectByPrimaryKey(request.getId());
        if (app == null) {
            throw new QueryException();
        }
        app.setName(request.getName());
        app.setDesc(request.getDesc());

        String imgUrl = mUploadService.upload(request.getCover(), UploadService.IMG);
        if (imgUrl != null) {
            String oldUrl = app.getCoverImageUrl();
            if (!TextUtil.isEmpty(oldUrl)) {
                mUploadService.delete(oldUrl);
            }
            app.setCoverImageUrl(imgUrl);
        }

        app.setUpdateOn(System.currentTimeMillis());
        mAppMapper.updateByPrimaryKey(app);
    }

    public App query(int id) throws QueryException {
        App app = mAppMapper.selectByPrimaryKey(id);
        if (app == null) {
            throw new QueryException("应用不存在");
        }
        return app;
    }


    public List<App> queryAll() {
        return mAppMapper.selectAll();
    }


    /**
     * 删除应用，包括应用的所有apk
     *
     * @param id appId
     * @throws QueryException
     */
    @Transactional
    public void delete(int id) throws QueryException {
        App app = mAppMapper.selectByPrimaryKey(id);
        if (app == null) {
            throw new QueryException();
        }
        // 删除时将七牛云上的文件一起删除
        // 应用自身图片
        mUploadService.delete(app.getCoverImageUrl());
        // 应用对应的所有apk
//        List<Apk>
        mUploadService.deleteAll(mApkMapper.selectAllUrlByApp(id));
        // 级联删除
        mAppMapper.deleteByPrimaryKey(id);
    }
}
