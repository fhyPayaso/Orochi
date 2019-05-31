package com.fhypayaso.orochi.model.request;

import org.springframework.web.multipart.MultipartFile;

public class AppRequest {

    private String name;

    private String desc;

    private MultipartFile cover;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public MultipartFile getCover() {
        return cover;
    }

    public void setCover(MultipartFile cover) {
        this.cover = cover;
    }
}
