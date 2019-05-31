package com.fhypayaso.orochi.model.exception;

import com.fhypayaso.orochi.model.base.ApiException;

public class FileTypeException extends ApiException {


    public FileTypeException() {
        super(1008, "文件格式错误");
    }
}
