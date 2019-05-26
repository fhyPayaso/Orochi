package com.fhypayaso.orochi.controller;

import com.fhypayaso.orochi.bean.User;
import com.fhypayaso.orochi.config.ConfigKey;
import com.fhypayaso.orochi.model.base.ApiResponse;
import com.fhypayaso.orochi.model.exception.ParamException;
import com.fhypayaso.orochi.service.TokenService;
import com.fhypayaso.orochi.service.UserService;
import com.fhypayaso.orochi.utils.RegexpUtil;
import com.fhypayaso.orochi.utils.TextUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService mUserService;

    @Autowired
    TokenService mTokenService;

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestParam("phone") String phone,
                                        @RequestParam("password") String password) throws Exception {
        if (!checkForm(phone, password)) {
            throw new ParamException();
        }
        String token = mUserService.register(phone, password);
        return new ApiResponse<>(token);
    }


    @PostMapping("/login")
    public ApiResponse<String> login(@RequestParam("phone") String phone,
                                     @RequestParam("password") String password) throws Exception {

        if (!checkForm(phone, password)) {
            throw new ParamException();
        }
        String token = mUserService.login(phone, password);
        return new ApiResponse<>(token);
    }


    @GetMapping("/info")
    public ApiResponse<User> query(@RequestParam("phone") String phone) throws Exception {
        if (!TextUtil.isEmpty(phone)) {
            return new ApiResponse<>(mUserService.query(phone));
        }
        throw new ParamException();
    }


    @PostMapping("/token")
    public ApiResponse<String> checkToken(HttpServletRequest request) throws Exception {
        User user = (User) request.getAttribute(ConfigKey.KEY_USER);
        return new ApiResponse<>(mUserService.loginByToken(user));
    }


    @DeleteMapping("/delete")
    public ApiResponse deleteUser(@RequestParam("phone") String phone) throws Exception {
        return new ApiResponse(mUserService.delete(phone));
    }


    private boolean checkForm(String phone, String password) {
        return !TextUtil.isEmpty(password)
                && !TextUtil.isEmpty(phone)
                && RegexpUtil.checkMobile(phone);
    }

}
