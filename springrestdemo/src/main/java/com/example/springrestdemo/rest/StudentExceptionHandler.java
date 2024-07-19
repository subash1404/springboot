package com.example.springrestdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler {
    @ExceptionHandler
    // The error would be of the type StudentError Response 
    //and We are gonna handle an exception of type StudentNotFoundException
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        // Response Entity consists of a body which contains the response and the status code
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        // The error is converted into JAVA POCO by jackson behind the scenes
        
        // However a response entity can be created with an empty body
        // return new ResponseEntity<>(HttpStatus.NOT_FOUND); // It will show a 404 page for this

    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleOtherExceptions(Exception exc){
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
