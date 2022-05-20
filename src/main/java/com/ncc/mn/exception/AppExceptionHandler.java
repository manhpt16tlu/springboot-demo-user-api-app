package com.ncc.mn.exception;


import com.ncc.mn.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class AppExceptionHandler {

    //handle user service
    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ExceptionResponse> handleUserServiceException(UserServiceException ex, WebRequest req){
        ExceptionResponse response = ExceptionResponse.builder().timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleLogin(BadCredentialsException ex, WebRequest req){
        ExceptionResponse response = ExceptionResponse.builder().timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


    //handle all exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAll(Exception ex, WebRequest req){
        ExceptionResponse response = ExceptionResponse.builder().timestamp(new Date()).message(ex.getMessage()).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
