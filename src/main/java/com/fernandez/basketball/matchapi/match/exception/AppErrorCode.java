package com.fernandez.basketball.matchapi.match.exception;

import org.springframework.http.HttpStatus;

public enum AppErrorCode {
    MATCH_NOT_FOUND(HttpStatus.NOT_FOUND, "Match not found");

    private final HttpStatus status;
    private final String message;

    AppErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus status() {
        return status;
    }

    public String message() {
        return message;
    }
}
