package com.test.doofus.controller;

import com.test.doofus.constants.ResponseType;
import com.test.doofus.exception.InvalidRequestException;
import com.test.doofus.model.http.RestApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<RestApiResponse<?>> invalidRequestException(InvalidRequestException ex) {
        return ResponseEntity.badRequest().body(RestApiResponse.from(ResponseType.BAD_REQUEST, ex.getMessage()));
    }
}
