package com.fhypayaso.orochi.controller;

import com.fhypayaso.orochi.bean.Apk;
import com.fhypayaso.orochi.model.base.ApiResponse;
import com.fhypayaso.orochi.model.exception.QueryException;
import com.fhypayaso.orochi.model.request.ApkRequest;
import com.fhypayaso.orochi.service.ApkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apk")
public class ApkController {

    @Autowired
    ApkService mApkService;


    @PostMapping("/create")
    public ApiResponse createApk(ApkRequest request) throws Exception {
        mApkService.create(request);
        return new ApiResponse();
    }


    @PostMapping("/update")
    public ApiResponse updateApk(ApkRequest request) throws Exception {
        mApkService.update(request);
        return new ApiResponse();
    }

    @GetMapping("/query/{apk_id}")
    public ApiResponse<Apk> queryApk(@PathVariable("apk_id") int apkId) throws QueryException {
        return new ApiResponse<>(mApkService.query(apkId));
    }

    @GetMapping("/query")
    public ApiResponse<List<Apk>> queryAll() {
        return new ApiResponse<>(mApkService.queryAll());
    }


    @DeleteMapping("/delete/{apk_id}")
    public ApiResponse deleteApk(@PathVariable("apk_id") int apkId) throws QueryException {
        mApkService.delete(apkId);
        return new ApiResponse();
    }


    @GetMapping("/query/files/{app_id}")
    public ApiResponse<List<String>> queryAllApkFiles(@PathVariable("app_id") int appId) {
        return new ApiResponse<>(mApkService.queryAllUrlByApp(appId));
    }


}
