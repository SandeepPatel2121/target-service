package com.target.service.custom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * @author Sandeep Patel
 * @Since 18-10-2022
 * @description
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyInUseException extends RuntimeException {

    public ResourceAlreadyInUseException(String message) {
        super(message);
    }

    public ResourceAlreadyInUseException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s resource is already in use %s : '%s'", resourceName, fieldName, fieldValue));
    }

}
