package com.epam.brest.courses.rest_app.exception;

/**
 * Transport not found exception
 */
public class TransportNotFoundException extends RuntimeException {
    public TransportNotFoundException(Integer transportId){
        super("Transport not found for id: " + transportId);
    }
}
