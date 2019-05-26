package com.fhypayaso.orochi.service;

import com.fhypayaso.orochi.bean.App;
import com.fhypayaso.orochi.dao.AppMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired
    AppMapper mAppMapper;


    public void create(App app) {
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
