package com.viseo.fx.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(UserNotFoundException.class)
    public void handleNotFound(UserNotFoundException ex) {
        logger.error("Requested User not found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(InvalidUserRequestException.class)
    public void handleBadRequest(InvalidUserRequestException ex) {
        logger.error("Invalid User supplied in request");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class)
    public void handleGeneralError(Exception ex) {
        logger.error("An error occurred processing request" + ex);
    }
}
