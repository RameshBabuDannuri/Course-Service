package com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException
                                                                     rnfe, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceNotCreatedException.class)
    public ResponseEntity<?> handleResourceNotCreatedException(ResourceNotCreatedException rncf , HttpServletRequest request)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Resource Not Created");
        errorDetail.setDetail(rncf.getMessage());
        return new ResponseEntity(errorDetail , null , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourcesNotAvailableException.class)
    public ResponseEntity<?> handleResourcesNotAvailable(ResourcesNotAvailableException rnae, HttpServletRequest request){

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setStatus(HttpStatus.NO_CONTENT.value());
        errorDetail.setTitle("Resources Not Available");
        errorDetail.setDetail(rnae.getMessage());
        return new ResponseEntity(errorDetail , null , HttpStatus.NO_CONTENT);
    }
}