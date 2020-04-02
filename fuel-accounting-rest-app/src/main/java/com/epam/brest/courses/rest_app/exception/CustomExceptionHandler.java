package com.epam.brest.courses.rest_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private String INCORRECT_REQUEST = "INCORRECT_REQUEST";

    /**
     * Handle fuel not found exception.
     * @param ex FuelNotFoundException.
     * @param request WebRequest.
     * @return ResponseEntity.
     */
    @ExceptionHandler(FuelNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleFuelNotFoundException (FuelNotFoundException ex, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle transport not found exception.
     * @param ex FuelNotFoundException.
     * @param request WebRequest.
     * @return ResponseEntity.
     */
    @ExceptionHandler(TransportNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleTransportNotFoundException (TransportNotFoundException ex, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
