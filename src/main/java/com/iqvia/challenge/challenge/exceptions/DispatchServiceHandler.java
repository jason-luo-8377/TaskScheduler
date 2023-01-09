package com.iqvia.challenge.challenge.exceptions;

import com.iqvia.challenge.challenge.model.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.iqvia.challenge.challenge.exceptions.BaseException.ERROR_CODE_VALIDATION;

/**
 * Exception handler which catches possible exceptions
 * We should list all possible exceptions here so the formats of endpoint responses are consistent
 * Note: for challenge purpose, only a few are listed here
 */
@ControllerAdvice
public class DispatchServiceHandler {
    /**
     * Handler for HttpMessageNotReadableException which is mostly validating the request body in json
     * Failure scenarios include but not limited:
     * 1. Invalid JSON
     * 2. Field validation failure(e.g. a date field but invalid date provided)
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<String> handleMessageNotReadableException(HttpMessageNotReadableException e) {
        //TODO, we will need to format the error message to make it user friendly
        return RestResponse.fail(ERROR_CODE_VALIDATION, e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * This is the handler for CustomValidationException
     * @param e
     * @return
     */
    @ExceptionHandler(value = CustomValidationException.class)
    public ResponseEntity handleCustomValidationException(CustomValidationException e) {
        return RestResponse.fail(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * This is the handler for SchedulerServiceException
     * @param e
     * @return
     */
    @ExceptionHandler(value = SchedulerServiceException.class)
    public ResponseEntity handleSchedulerServiceException(SchedulerServiceException e) {
        //We can return a custom http code if needed
        return RestResponse.fail(e.getCode(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
