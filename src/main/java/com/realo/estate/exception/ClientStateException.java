package com.realo.estate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientStateException extends RuntimeException {

    public ClientStateException() {
        super();
    }

    public ClientStateException(String message) {
        super(message);
    }

    public ClientStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientStateException(Throwable cause) {
        super(cause);
    }
}