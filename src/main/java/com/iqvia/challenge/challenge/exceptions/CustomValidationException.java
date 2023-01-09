package com.iqvia.challenge.challenge.exceptions;

public class CustomValidationException extends BaseException {
    public CustomValidationException(String description) {
        super(ERROR_CODE_VALIDATION, description);
    }
}
