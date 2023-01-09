package com.iqvia.challenge.challenge.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseException extends Exception {
    public static final String ERROR_CODE_VALIDATION = "VALIDATION_ERROR";
    public static final String ERROR_CODE_APPLICATION = "APPLICATION_ERROR";

    private String code;

    public BaseException(String code, String description, Throwable throwable) {
        super(description, throwable);
        this.code = code;
    }

    public BaseException(String code, String description) {
        this(code, description, null);
    }
}
