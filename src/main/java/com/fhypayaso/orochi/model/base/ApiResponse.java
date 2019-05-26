package com.fhypayaso.orochi.model.base;

public class ApiResponse<T> {

    // 状态码
    private int code;

    // 信息描述
    private String message;

    // 数据实体
    private T data;

    public ApiResponse() {
        this.code = 1000;
        this.message = "success";
    }

    public ApiResponse(T data) {
        this.code = 1000;
        this.message = "success";
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
