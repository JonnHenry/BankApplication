package com.devsu.hackerearth.backend.account.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NonExecutableTransactionExeption extends RuntimeException {
    
    public NonExecutableTransactionExeption(String message) {
        super(message);
    }
}
