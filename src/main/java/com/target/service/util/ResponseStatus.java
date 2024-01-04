package com.target.service.util;

import org.springframework.http.HttpStatus;

public final class ResponseStatus {

    private ResponseStatus() {
    }

    public static ResponseModel success(String message, Object data, HttpStatus httpStatus) {
        ResponseModel rs = new ResponseModel();

        rs.setMessage(message);
        rs.setData(data);
        rs.setHttpStatus(httpStatus);
        rs.setStatus(httpStatus.value());

        return rs;
    }

    public static ResponseModel error(String message, Object errors, HttpStatus httpStatus) {
        ResponseModel rs = new ResponseModel();

        rs.setMessage(message);
        rs.setHttpStatus(httpStatus);
        rs.setErrors(errors);
        rs.setStatus(httpStatus.value());

        return rs;
    }

}
