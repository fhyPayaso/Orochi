package com.fhypayaso.orochi.model.exception;

public class UploadExecption extends ApiException {

    public UploadExecption() {
        super(1010, "七牛云上传失败");
    }
}
