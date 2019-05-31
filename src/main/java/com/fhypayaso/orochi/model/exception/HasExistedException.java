package com.fhypayaso.orochi.model.exception;

import com.fhypayaso.orochi.model.base.ApiException;

public class HasExistedException extends ApiException {

    public HasExistedException(String msg) {
        super(1004, msg);
    }
}
