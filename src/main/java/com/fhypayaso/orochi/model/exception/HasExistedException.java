package com.fhypayaso.orochi.model.exception;

public class HasExistedException extends ApiException {

    public HasExistedException(String msg) {
        super(1004, msg);
    }
}
