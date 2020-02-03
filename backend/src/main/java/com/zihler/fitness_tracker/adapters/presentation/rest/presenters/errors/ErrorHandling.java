package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.errors;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.EmptyMuscleGroupsRequest;
import com.zihler.fitness_tracker.domain.exceptions.InvalidTitleException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.I_AM_A_TEAPOT;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class ErrorHandling {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorViewModel> handleGenericException(Exception bad) {
        return status(I_AM_A_TEAPOT).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = InvalidTitleException.class)
    public ResponseEntity<ErrorViewModel> handleInvalidTitleException(Exception bad) {
        return status(BAD_REQUEST).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = EmptyMuscleGroupsRequest.class)
    public ResponseEntity<ErrorViewModel> handleEmptyMuscleGroupsRequest(Exception bad){
        return status(BAD_REQUEST).body(new ErrorViewModel(bad.getMessage()));
    }
}
