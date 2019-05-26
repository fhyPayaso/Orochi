package com.fhypayaso.orochi.controller;

import com.fhypayaso.orochi.bean.App;
import com.fhypayaso.orochi.dao.AppMapper;
import com.fhypayaso.orochi.model.base.ApiResponse;
import com.fhypayaso.orochi.service.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    AppService mAppService;

    @PostMapping("/create")
    public ApiResponse createApp(@RequestBody App app) {
        mAppService.create(app);
        return new ApiResponse();
    }


    @PostMapping("/update")
    public ApiResponse updateApp(@RequestBody App app) {
        mAppService.update(app);
        return new ApiResponse();
    }

    /**
     * 查询具体应用
     *
     * @param appId AppId
     * @return 应用信息
     */
    @GetMapping("/query/{app_id}")
    public ApiResponse<App> queryApp(@PathVariable("app_id") int appId) {
        return new ApiResponse<>(mAppService.query(appId));
    }

    /**
     * 查询全部应用
     *
     * @return 全部应用信息
     */
    @GetMapping("/query")
    public ApiResponse<List<App>> queryApps() {
        return new ApiResponse<>(mAppService.queryAll());
    }

    /**
     * 删除指定应用
     *
     * @param appId AppId
     * @return
     */
    @DeleteMapping("/delete/{app_id}")
    public ApiResponse deleteApp(@PathVariable("app_id") int appId) {
        mAppService.delete(appId);
        return new ApiResponse();
    }


}
