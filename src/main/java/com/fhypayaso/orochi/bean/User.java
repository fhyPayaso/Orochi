package com.fhypayaso.orochi.bean;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String phone;

    private String password;

    private String name;

    private Integer permission;

    private String coverImageUrl;

    private Long registerOn;

    private Long updateOn;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl == null ? null : coverImageUrl.trim();
    }

    public Long getRegisterOn() {
        return registerOn;
    }

    public void setRegisterOn(Long registerOn) {
        this.registerOn = registerOn;
    }

    public Long getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Long updateOn) {
        this.updateOn = updateOn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", password=").append(password);
        sb.append(", name=").append(name);
        sb.append(", permission=").append(permission);
        sb.append(", coverImageUrl=").append(coverImageUrl);
        sb.append(", registerOn=").append(registerOn);
        sb.append(", updateOn=").append(updateOn);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}