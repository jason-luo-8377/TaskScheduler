package com.iqvia.challenge.challenge.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class RestResponse {
    private String code;
    private String description;
    private Object data;

    private RestResponse() {}

    public static ResponseEntity ok(Object data) {
        return ok(data, HttpStatus.ACCEPTED);
    }

    public static ResponseEntity ok(Object data, HttpStatus status) {
        RestResponse response = new RestResponse();
        response.data = data;

        return new ResponseEntity(response, status);
    }

    public static ResponseEntity fail(String errorCode, String errorMessage) {
        return fail(errorCode, errorMessage, HttpStatus.OK);
    }

    public static ResponseEntity fail(String errorCode, String errorMessage, HttpStatus status) {
        RestResponse response = new RestResponse();
        response.code = errorCode;
        response.description = errorMessage;

        return new ResponseEntity(response, status);
    }

    public static ResponseEntity fail(String errorCode, String errorMessage, HttpStatus status, Object data) {
        RestResponse response = new RestResponse();
        response.code = errorCode;
        response.description = errorMessage;
        response.data = data;

        return new ResponseEntity(response, status);
    }
}
