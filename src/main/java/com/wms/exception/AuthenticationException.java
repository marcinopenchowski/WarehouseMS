package com.wms.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends RuntimeException {

    private final HttpStatus code;
    public AuthenticationException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }
}
