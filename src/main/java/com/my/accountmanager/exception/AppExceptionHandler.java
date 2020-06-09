package com.my.accountmanager.exception;


import com.my.accountmanager.exception.configuration.TransactionServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {AccountServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(AccountServiceException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), LocalDate.now());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {TransactionServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(TransactionServiceException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), LocalDate.now());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
