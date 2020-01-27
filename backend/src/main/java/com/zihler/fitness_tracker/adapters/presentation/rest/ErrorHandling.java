package com.zihler.fitness_tracker.adapters.presentation.rest;

import com.zihler.fitness_tracker.adapters.presentation.rest.json.ErrorViewModel;
import com.zihler.fitness_tracker.domain.exceptions.InvalidTitleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ErrorHandling {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorViewModel> handleGenericException(Exception bad) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                .body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value= InvalidTitleException.class)
    public ResponseEntity<ErrorViewModel> handleInvalidTitleException(Exception bad){
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ErrorViewModel(bad.getMessage()));
    }
}
