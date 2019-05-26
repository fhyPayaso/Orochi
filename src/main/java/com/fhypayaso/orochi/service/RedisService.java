package com.fhypayaso.orochi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> mRedisTemplate;


    public void setData(String key, Object value) {
        mRedisTemplate.opsForValue().set(key, value);
    }

    public Object getData(String key){
        return mRedisTemplate.opsForValue().get(key);
    }


    public void deleteData(String key) {
        mRedisTemplate.delete(key);
    }


}
