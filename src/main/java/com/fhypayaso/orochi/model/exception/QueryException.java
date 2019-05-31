package com.fhypayaso.orochi.model.exception;

import com.fhypayaso.orochi.model.base.ApiException;

public class QueryException extends ApiException {
    public QueryException() {
        super(1002, "查询异常");
    }

    public QueryException(String msg) {
        super(1002, msg);
    }
}
