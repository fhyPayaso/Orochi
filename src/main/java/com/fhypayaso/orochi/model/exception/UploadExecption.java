package com.fhypayaso.orochi.model.exception;

import com.fhypayaso.orochi.model.base.ApiException;

public class UploadExecption extends ApiException {

    public UploadExecption() {
        super(1010, "七牛云上传失败");
    }
}
