package com.fhypayaso.orochi.model.exception;

public class FileTypeException extends ApiException {


    public FileTypeException() {
        super(1008, "文件格式错误");
    }
}
