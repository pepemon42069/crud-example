package com.test.doofus.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ResponseType {
    GENERIC_SUCCESS(1000,  "success"),
    GENERIC_FAILURE(1001,  "something went wrong"),
    BAD_REQUEST(1002, "invalid request")
    ;

    Integer code;
    String message;
}
