package com.fhypayaso.orochi.service;

import com.fhypayaso.orochi.bean.User;
import com.fhypayaso.orochi.config.ConfigKey;
import com.fhypayaso.orochi.dao.UserMapper;
import com.fhypayaso.orochi.model.exception.ApiException;
import com.fhypayaso.orochi.model.exception.HasExistedException;
import com.fhypayaso.orochi.model.exception.QueryException;
import com.fhypayaso.orochi.utils.EncryptUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {


    @Autowired
    UserMapper mUserMapper;

    @Autowired
    TokenService mTokenService;

    @Autowired
    RedisService mRedisService;

    /**
     * @param phone    手机号
     * @param password 密码
     * @return token
     * @throws Exception
     */
    @Transactional
    public String register(String phone, String password) throws Exception {

        if (null != mUserMapper.selectByPhone(phone)) {
            throw new HasExistedException("该手机号已被注册");
        }

        User user = new User();
        user.setPhone(phone);
        user.setPassword(EncryptUtil.encrypt(password));
        long curTime = System.currentTimeMillis();
        user.setRegisterOn(curTime);
        user.setUpdateOn(curTime);
        System.out.println(user.toString());
        mUserMapper.insert(user);

        User queryUser = query(phone);
        String token = mTokenService.createToken(queryUser);
        mRedisService.setData(ConfigKey.KEY_UID + queryUser.getId(), token);
        return token;
    }


    /**
     * @param phone    手机号
     * @param password 密码
     * @return token
     * @throws Exception
     */
    public String login(String phone, String password) throws Exception {

        if (null == mUserMapper.selectByPhone(phone)) {
            throw new QueryException("该用户不存在");
        }
        User user = mUserMapper.selectByPhone(phone);
        if (!EncryptUtil.check(password, user.getPassword())) {
            throw new ApiException(1005, "密码错误");
        }

        String token = mTokenService.createToken(user);
        mRedisService.setData(ConfigKey.KEY_UID + user.getId(), token);
        return token;
    }


    /**
     * @param user 用户信息
     * @return token
     */
    public String loginByToken(User user) {
        String token = mTokenService.createToken(user);
        mRedisService.setData(ConfigKey.KEY_UID + user.getId(), token);
        return token;
    }


    /**
     * @param id 用户id
     * @return 用户信息
     * @throws Exception
     */
    public User query(int id) throws Exception {
        User user = mUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new QueryException("该用户不存在");
        }
        return user;
    }


    /**
     * @param phone 用户手机
     * @return 用户信息
     * @throws Exception
     */
    public User query(String phone) throws Exception {
        User user = mUserMapper.selectByPhone(phone);
        if (user == null) {
            throw new QueryException("该用户不存在");
        }
        return user;
    }

    public int delete(String phone) throws Exception {
        User user = query(phone);
        if (user == null) {
            throw new QueryException("用户不存在");
        }

        return mUserMapper.deleteByPrimaryKey(user.getId());
    }

}
