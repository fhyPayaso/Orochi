package com.fhypayaso.orochi.interceptor;


import com.fhypayaso.orochi.bean.User;
import com.fhypayaso.orochi.config.ConfigKey;
import com.fhypayaso.orochi.model.exception.QueryException;
import com.fhypayaso.orochi.service.TokenService;
import com.fhypayaso.orochi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局token处理
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService mTokenService;

    @Autowired
    private UserService mUserService;


    private ArrayList<String> mUnCheckUrlList = new ArrayList<String>() {{
        add("/user/register");
        add("/user/login");
    }};


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(ConfigKey.KEY_TOKEN);
        String url = request.getRequestURI();

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        if (mUnCheckUrlList.parallelStream().anyMatch(url::contains)) {
            return true;
        }

        int userId = mTokenService.checkToken(token);
        User user = mUserService.query(userId);
        if (user == null) {
            throw new QueryException("该用户不存在");
        }
        request.setAttribute(ConfigKey.KEY_USER, user);
        return true;
    }
}
