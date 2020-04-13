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

    public static final String FUEL_NOT_FOUND = "fuel.not_found";
    public static final String TRANSPORT_NOT_FOUND = "transport.not_found";
    public static final String VALIDATION_ERROR = "validation_error";

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
        ErrorResponse error = new ErrorResponse(FUEL_NOT_FOUND, details);
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
        ErrorResponse error = new ErrorResponse(TRANSPORT_NOT_FOUND, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle illegal argument exception.
     * @param ex Exception.
     * @param request WebRequest.
     * @return ResponseEntity.
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(VALIDATION_ERROR, ex),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
