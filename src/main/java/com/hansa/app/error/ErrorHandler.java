/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author sushant
 */
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    
     @ExceptionHandler(value = {RuntimeException.class,RequestException.class, UnAuthoriseException.class})
     public ResponseEntity<Object> handle(Exception ex) {
       //  ex.printStackTrace();
        return handleExceptions(ex);
    }
   
    private ResponseEntity<Object> handleExceptions(Throwable ex) {
        if (ex instanceof RequestException) {
            return new ResponseEntity(new ErrorResponse("Request processing failed", ex.getMessage()), HttpStatus.BAD_REQUEST);
        } else if (ex instanceof UnAuthoriseException) {
            return new ResponseEntity(new ErrorResponse("Request processing failed", ex.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        return error();
        
    }
    
    private ResponseEntity<Object> error() {
        return new ResponseEntity(new ErrorResponse("ERROR", "System problem"), HttpStatus.INTERNAL_SERVER_ERROR);
}
    
}
