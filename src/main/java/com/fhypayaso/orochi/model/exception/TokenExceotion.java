package com.fhypayaso.orochi.model.exception;

import com.fhypayaso.orochi.model.base.ApiException;

public class TokenExceotion extends ApiException {
    public TokenExceotion(String msg) {
        super(1007, msg);
    }
}
