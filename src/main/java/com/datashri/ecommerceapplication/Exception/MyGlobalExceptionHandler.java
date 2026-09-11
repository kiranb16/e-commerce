package com.datashri.ecommerceapplication.Exception;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(@NonNull MethodArgumentNotValidException ex) {

        Map<String, String> response = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(err -> {

            String fieldName = ((FieldError) err).getField();
            String errorMessage = err.getDefaultMessage();

            response.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ResponseStatusNOtFoundException.class)
    public ResponseEntity<String> myResponseNotfoundException(ResponseStatusNOtFoundException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> myApiException(ApiException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }
}

