package com.cmcba.api.exceptions.controllers;

import com.cmcba.api.exceptions.customs.UnauthorizedException;
import com.cmcba.api.models.responses.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author claudio.vilas
 * dte 10/2023
 */

@RestControllerAdvice
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UauthorizedException {
    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<ErrorResponse> unauthorizedExceptionHandler(Exception e, HttpServletRequest request){
        var response = ErrorResponse.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED.name())
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
