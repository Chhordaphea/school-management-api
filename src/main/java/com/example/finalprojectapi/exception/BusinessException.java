package com.example.finalprojectapi.exception;


import com.example.finalprojectapi.common.api.StatusCode;

/**
 * Handle exception for Business Exception
 */
public class BusinessException extends RuntimeException {

    private final StatusCode errorCode;

    public BusinessException(StatusCode errorCode, String message) {

        super(message);
        this.errorCode = errorCode;

    }

    public BusinessException(StatusCode errorCode) {

        super(errorCode.getMessage());
        this.errorCode = errorCode;

    }

    public BusinessException(StatusCode errorCode, Throwable e) {

        this(errorCode);
//        AppLogManager.error(e);

    }

    public StatusCode getErrorCode() {
        return errorCode;
    }
}
