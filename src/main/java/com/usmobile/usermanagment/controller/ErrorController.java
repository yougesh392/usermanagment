package com.usmobile.usermanagment.controller;

import com.usmobile.usermanagment.utils.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

public class ErrorController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> handleResponseStatusException(ResponseStatusException ex) {
        ApiError errorDetails = new ApiError(ex.getStatusCode().value(), ex.getReason());
        return new ResponseEntity<>(errorDetails, ex.getStatusCode());
    }
}
