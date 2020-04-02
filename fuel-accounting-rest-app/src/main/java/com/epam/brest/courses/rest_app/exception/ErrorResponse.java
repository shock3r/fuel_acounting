package com.epam.brest.courses.rest_app.exception;

import java.util.List;

/**
 * Error response class.
 */
public class ErrorResponse {

    private String message;
    private List<String> details;

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public List<String> getDetails() {
        return details;
    }

    public ErrorResponse setDetails(List<String> details) {
        this.details = details;
        return this;
    }
}
