package com.fhypayaso.orochi.interceptor.exception;

import com.fhypayaso.orochi.model.base.ApiException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常统一处理
 */
@ControllerAdvice
public class ExceptionInterceptor {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ExceptionResponse exceptionHandler(Exception exception) {
        // 主动异常
        if (exception instanceof ApiException) {
            return new ExceptionResponse(
                    ((ApiException) exception).getCode(),
                    ((ApiException) exception).getMsg()
            );
        }
        // 未知异常
        return new ExceptionResponse(9999, exception.getMessage());
    }
}
