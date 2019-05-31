package com.fhypayaso.orochi.model.request;

import org.springframework.web.multipart.MultipartFile;

public class ApkRequest {

    private Integer id;

    private Integer appId;

    private String versionCode;

    private MultipartFile apkFile;

    private String desc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public MultipartFile getApkFile() {
        return apkFile;
    }

    public void setApkFile(MultipartFile apkFile) {
        this.apkFile = apkFile;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
