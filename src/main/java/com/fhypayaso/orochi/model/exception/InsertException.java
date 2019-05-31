package com.fhypayaso.orochi.model.exception;

import com.fhypayaso.orochi.model.base.ApiException;

public class InsertException extends ApiException {

    public InsertException() {
        super(1003, "创建异常");
    }
}
