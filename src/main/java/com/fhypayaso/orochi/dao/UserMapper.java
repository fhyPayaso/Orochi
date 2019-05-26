package com.fhypayaso.orochi.dao;

import com.fhypayaso.orochi.bean.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    User selectByPhone(String phone);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}