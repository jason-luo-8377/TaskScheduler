package com.iqvia.challenge.challenge.exceptions;

public class SchedulerServiceException extends BaseException {
    public SchedulerServiceException(String description, Throwable throwable) {
        super(ERROR_CODE_APPLICATION, description, throwable);
    }
}
