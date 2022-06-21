package com.realo.estate.web.controller.handler;

import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.web.controller.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    public static final int NOT_FOUND_CODE = 404;
    private static final int INTERNAL_SERVER_ERROR_CODE = 500;
    private static final String INTERNAL_SERVER_ERROR_MSG = "Something went wrong!";

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handle(ResourceNotFoundException e) {
        return new ResponseError(NOT_FOUND_CODE, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handle() {
        return new ResponseError(INTERNAL_SERVER_ERROR_CODE, INTERNAL_SERVER_ERROR_MSG);
    }
}