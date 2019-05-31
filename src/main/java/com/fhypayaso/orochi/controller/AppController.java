package com.fhypayaso.orochi.controller;

import com.fhypayaso.orochi.bean.App;
import com.fhypayaso.orochi.model.base.ApiResponse;
import com.fhypayaso.orochi.model.exception.ParamException;
import com.fhypayaso.orochi.model.exception.QueryException;
import com.fhypayaso.orochi.model.request.AppRequest;
import com.fhypayaso.orochi.service.AppService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    AppService mAppService;

    @PostMapping("/create")
    public ApiResponse createApp(AppRequest request) throws Exception {
        mAppService.create(request);
        return new ApiResponse();
    }


    @PostMapping("/update")
    public ApiResponse updateApp(AppRequest request) throws Exception {
        if (request.getId() == 0) {
            throw new ParamException();
        }
        mAppService.update(request);
        return new ApiResponse();
    }

    /**
     * 查询具体应用
     *
     * @param appId AppId
     * @return 应用信息
     */
    @GetMapping("/query/{app_id}")
    public ApiResponse<App> queryApp(@PathVariable("app_id") int appId) throws QueryException {
        return new ApiResponse<>(mAppService.query(appId));
    }

    /**
     * 查询全部应用
     *
     * @return 全部应用信息
     */
    @GetMapping("/query")
    public ApiResponse<PageInfo<App>> queryApps(@RequestParam(defaultValue = "0") int offset,
                                                @RequestParam(defaultValue = "20") int count) {
        return new ApiResponse<>(mAppService.queryAll(offset, count));
    }

    /**
     * 删除指定应用
     *
     * @param appId AppId
     * @return
     */
    @DeleteMapping("/delete/{app_id}")
    public ApiResponse deleteApp(@PathVariable("app_id") int appId) throws QueryException {
        mAppService.delete(appId);
        return new ApiResponse();
    }


}
