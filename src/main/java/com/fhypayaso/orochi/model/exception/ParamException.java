package com.fhypayaso.orochi.model.exception;

import com.fhypayaso.orochi.model.base.ApiException;

public class ParamException extends ApiException {

    public ParamException() {
        super(1001, "参数校验失败");
    }
}
