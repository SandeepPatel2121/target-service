package com.target.service.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotAvailableException extends RuntimeException {

    public ResourceNotAvailableException(String message) {}

    public ResourceNotAvailableException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s resource not available %s : '%s'", resourceName, fieldName, fieldValue));
    }

}
