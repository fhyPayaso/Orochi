package com.fhypayaso.orochi.model.exception;

public class ParamException extends ApiException {

    public ParamException() {
        super(1001, "参数校验失败");
    }
}
