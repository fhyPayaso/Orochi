package com.fhypayaso.orochi.service;

import com.fhypayaso.orochi.bean.App;
import com.fhypayaso.orochi.dao.AppMapper;
import com.fhypayaso.orochi.model.request.AppRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.List;

@Service
public class AppService {

    @Autowired
    AppMapper mAppMapper;

    @Autowired
    UploadService mUploadService;


    public void create(AppRequest request) throws Exception {

        String imgUrl = "";
        if (request.getCover() != null && !request.getCover().isEmpty()) {
            MultipartFile multipartFile = request.getCover();
            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
            imgUrl = mUploadService.uploadImage(inputStream, multipartFile.getOriginalFilename());
        }

        App app = new App();
        app.setName(request.getName());
        app.setDesc(request.getDesc());
        app.setCoverImageUrl(imgUrl);
        long curTime = System.currentTimeMillis();
        app.setCreatedOn(curTime);
        app.setUpdateOn(curTime);

        mAppMapper.insert(app);
    }

    public void update(App app) {
        mAppMapper.updateByPrimaryKey(app);
    }

    public App query(int id) {
        return mAppMapper.selectByPrimaryKey(id);
    }


    public List<App> queryAll() {
        return mAppMapper.selectAll();
    }


    public void delete(int id) {
        mAppMapper.deleteByPrimaryKey(id);
    }

}
