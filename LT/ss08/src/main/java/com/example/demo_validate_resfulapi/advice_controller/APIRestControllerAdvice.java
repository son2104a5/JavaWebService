package com.example.demo_validate_resfulapi.advice_controller;

import com.example.demo_validate_resfulapi.model.dto.resonse.DataErrorResponse;
import com.example.demo_validate_resfulapi.model.dto.resonse.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class APIRestControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataErrorResponse<Map<String,String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
//        ex.getFieldErrors().forEach((error)->{
//            errors.put(error.getField(),error.getDefaultMessage());
//        });
        for(int i=0;i<ex.getAllErrors().size();i++){
            FieldError fieldError = ex.getFieldErrors().get(i);
            errors.put("error - "+i, fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new DataErrorResponse<>("errors",errors, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<DataErrorResponse<String>> handleNoSuchElementException(NoSuchElementException ex){
        return new ResponseEntity<>(new DataErrorResponse<>("errors",ex.getLocalizedMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
