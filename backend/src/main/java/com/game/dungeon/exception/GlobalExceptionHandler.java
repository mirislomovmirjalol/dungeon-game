package com.game.dungeon.exception;

import com.game.dungeon.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .message(ex.getMessage())
                .code("NOT_FOUND")
                .timestamp(Instant.now())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidGameStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidGameStateException(
            InvalidGameStateException ex, WebRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .message(ex.getMessage())
                .code("INVALID_GAME_STATE")
                .timestamp(Instant.now())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse error = ErrorResponse.builder()
                .message("Validation failed")
                .code("VALIDATION_FAILED")
                .timestamp(Instant.now())
                .path(request.getDescription(false))
                .details(details)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {
        List<String> details = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        ErrorResponse error = ErrorResponse.builder()
                .message("Validation failed")
                .code("VALIDATION_FAILED")
                .timestamp(Instant.now())
                .path(request.getDescription(false))
                .details(details)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .message("An unexpected error occurred")
                .code("INTERNAL_ERROR")
                .timestamp(Instant.now())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 