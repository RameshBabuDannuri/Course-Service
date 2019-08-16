package com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ResourcesNotAvailableException extends RuntimeException {
    public ResourcesNotAvailableException() {
    }

    public ResourcesNotAvailableException(String message) {
        super(message);
    }
}
