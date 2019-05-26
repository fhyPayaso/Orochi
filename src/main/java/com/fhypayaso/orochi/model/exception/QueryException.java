package com.fhypayaso.orochi.model.exception;

public class QueryException extends ApiException {
    public QueryException() {
        super(1002, "查询异常");
    }

    public QueryException(String msg) {
        super(1002, msg);
    }
}
