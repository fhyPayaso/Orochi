package com.fhypayaso.orochi.dao;

import com.fhypayaso.orochi.bean.Apk;
import java.util.List;

public interface ApkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Apk record);

    Apk selectByPrimaryKey(Integer id);

    List<Apk> selectAll();

    int updateByPrimaryKey(Apk record);
}