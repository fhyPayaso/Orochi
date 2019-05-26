package com.fhypayaso.orochi.controller;

import com.fhypayaso.orochi.bean.Apk;
import com.fhypayaso.orochi.dao.ApkMapper;
import com.fhypayaso.orochi.model.base.ApiResponse;
import com.fhypayaso.orochi.service.ApkService;

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
@RequestMapping("/apk")
public class ApkController {

    @Autowired
    ApkService mApkService;


    @PostMapping("/create")
    public ApiResponse createApk(@RequestBody Apk apk) {
        mApkService.create(apk);
        return new ApiResponse();
    }


    @PostMapping("/update")
    public ApiResponse updateApk(@RequestBody Apk apk) {
        mApkService.update(apk);
        return new ApiResponse();
    }

    @GetMapping("/query/{apk_id}")
    public ApiResponse<Apk> queryApk(@PathVariable("apk_id") int apkId) {
        return new ApiResponse<>(mApkService.query(apkId));
    }

    @GetMapping("/query")
    public ApiResponse<List<Apk>> queryAll() {
        return new ApiResponse<>(mApkService.queryAll());
    }


    @DeleteMapping("/delete/{apk_id}")
    public ApiResponse deleteApk(@PathVariable("apk_id") int apkId) {
        mApkService.delete(apkId);
        return new ApiResponse();
    }


}
