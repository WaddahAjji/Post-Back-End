package com.post.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnAuthenticatedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnAuthenticatedException(String message) {
        super(message);
    }
}