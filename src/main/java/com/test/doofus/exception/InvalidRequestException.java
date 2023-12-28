package com.test.doofus.exception;

import com.test.doofus.constants.ExceptionType;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(ExceptionType exceptionType) {
        super(exceptionType.getExceptionMessage());
    }

    public InvalidRequestException(ExceptionType exceptionType, Throwable throwable) {
        super(exceptionType.getExceptionMessage(), throwable);
    }
}
