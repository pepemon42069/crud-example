package com.test.doofus.model.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.doofus.constants.ResponseType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestApiResponse<T> {
    Integer code;
    String message;
    T data;

    public static <T> RestApiResponse<T> from(ResponseType responseType, T data) {
        RestApiResponse<T> restApiResponse = new RestApiResponse<>();
        restApiResponse.setCode(responseType.getCode());
        restApiResponse.setMessage(responseType.getMessage());
        restApiResponse.setData(data);
        return restApiResponse;
    }

    public static <T> RestApiResponse<T> from(ResponseType responseType) {
        return RestApiResponse.from(responseType, null);
    }
}
