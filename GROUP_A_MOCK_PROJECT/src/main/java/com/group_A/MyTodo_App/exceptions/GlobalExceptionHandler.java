package com.group_A.MyTodo_App.exceptions;

import com.group_A.MyTodo_App.entity.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTaskNotFoundException(final TaskNotFoundException e) {
        ErrorDetails errorResponse = new ErrorDetails();
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setErrorDetails(String.valueOf(HttpStatus.BAD_REQUEST));
        errorResponse.setErrorMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

