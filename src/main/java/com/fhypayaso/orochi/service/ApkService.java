package com.fhypayaso.orochi.service;

import com.fhypayaso.orochi.bean.Apk;
import com.fhypayaso.orochi.dao.ApkMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApkService {


    @Autowired
    ApkMapper mApkMapper;

    public void create(Apk apk) {
        mApkMapper.insert(apk);
    }

    public void update(Apk apk) {
        mApkMapper.updateByPrimaryKey(apk);
    }

    public Apk query(int id) {
        return mApkMapper.selectByPrimaryKey(id);
    }


    public List<Apk> queryAll() {
        return mApkMapper.selectAll();
    }


    public void delete(int id) {
        mApkMapper.deleteByPrimaryKey(id);
    }


}
