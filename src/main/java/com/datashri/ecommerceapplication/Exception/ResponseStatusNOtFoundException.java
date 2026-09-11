package com.datashri.ecommerceapplication.Exception;

import org.springframework.http.HttpStatus;

public class ResponseStatusNOtFoundException extends RuntimeException {

    String resourceName;
    String field;
    String  fieldName;
    Long fieldId;

    public ResponseStatusNOtFoundException(HttpStatus notFound, String s) {

    }

    public ResponseStatusNOtFoundException(String message, String resourceName, String fieldName, String field) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, field));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.field = field;
    }

    public ResponseStatusNOtFoundException(String message, String resourceName, String field, Long fieldId) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldId, field));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }
}
