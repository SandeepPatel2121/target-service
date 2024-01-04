package com.target.service.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ResponseModel {

    private int status;
    private String message;
    private HttpStatus httpStatus;
    private Object errors;
    private Object data;
}
