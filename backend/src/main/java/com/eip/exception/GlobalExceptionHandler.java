package com.eip.exception;

import com.eip.dto.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResource( DuplicateResourceException ex)
    {
        ErrorResponse response = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.CONFLICT.value(),

                ex.getMessage(),

                Collections.emptyMap()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    public ResponseEntity<ErrorResponse> handleValidationException (MethodArgumentNotValidException ex )
    {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                Optional.ofNullable( error.getDefaultMessage())
                                        .orElse("validation failed")
                        ));

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "validation failed",
                errors
        );

        return ResponseEntity
                .badRequest()
                .body( response );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException( Exception ex) {

        ErrorResponse response = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.INTERNAL_SERVER_ERROR.value(),

                "Unexpected error occurred",

                Collections.emptyMap()
        );

        return ResponseEntity
                .status( HttpStatus.INTERNAL_SERVER_ERROR )
                .body( response );
    }

    @ExceptionHandler( ResourceNotFoundException.class )
    public ResponseEntity<ErrorResponse> handleResourceNotFound( ResourceNotFoundException ex )
    {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Collections.emptyMap()
        );

        return ResponseEntity
                .status( HttpStatus.NOT_FOUND )
                .body( response );
    }

}
