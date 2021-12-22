package com.example.swagger.api;

import com.example.swagger.exception.BadRequestException;
import com.example.swagger.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestController
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String BAD_REQUEST_EXCEPTION = "Bad request exception";

    private static final String REASON_NOT_FOUND = "Entity Not Found";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleEntityNotFoundException(EntityNotFoundException exception) {
        Error error = new Error();

        error.setCode(Integer.toString(HttpStatus.NOT_FOUND.value()));
        error.setDescription(exception.getMessage());
        error.setReasonCode(REASON_NOT_FOUND);
        error.setSeeAlso("");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)

    public ResponseEntity<Error> handleBadRequestException(BadRequestException exception) {
        Error error = new Error();

        error.setCode(Integer.toString(HttpStatus.BAD_REQUEST.value()));
        error.setDescription(exception.getMessage());
        error.setReasonCode(BAD_REQUEST_EXCEPTION);
        error.setSeeAlso("");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
