package com.test.doofus.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ExceptionType {
    INVALID_EMAIL(100, "the email provided is not valid");

    Integer code;
    String message;

    public String getExceptionMessage() {
        return "error : " + code + " : " + message;
    }
}
