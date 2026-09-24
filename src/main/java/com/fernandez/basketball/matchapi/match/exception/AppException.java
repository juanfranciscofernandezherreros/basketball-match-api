package com.fernandez.basketball.matchapi.match.exception;

public class AppException extends RuntimeException {

    private final AppErrorCode errorCode;

    public AppException(AppErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

    public AppErrorCode errorCode() {
        return errorCode;
    }
}
