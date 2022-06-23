package com.realo.estate.web.controller.handler;

import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.web.controller.dto.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final int NOT_FOUND_CODE = 404;
    private static final int INTERNAL_SERVER_ERROR_CODE = 500;
    private static final String INTERNAL_SERVER_ERROR_MSG = "Something went wrong!";
    private static final String RESPONSE_ERROR_WAS_HANDLED_IN_CONTROLLER_ADVICE = "Response error was handled in controller advice :{}";

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handle(ResourceNotFoundException e) {
        ResponseError responseError = new ResponseError(NOT_FOUND_CODE, e.getMessage());
        log.info(RESPONSE_ERROR_WAS_HANDLED_IN_CONTROLLER_ADVICE, responseError);
        return responseError;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handle() {
        ResponseError responseError = new ResponseError(INTERNAL_SERVER_ERROR_CODE, INTERNAL_SERVER_ERROR_MSG);
        log.info(RESPONSE_ERROR_WAS_HANDLED_IN_CONTROLLER_ADVICE, responseError);
        return responseError;
    }
}