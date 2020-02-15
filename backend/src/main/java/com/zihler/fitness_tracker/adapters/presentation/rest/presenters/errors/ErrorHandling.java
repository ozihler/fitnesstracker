package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.errors;

import com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions.ConfiguringFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions.LoadingDataFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions.StoringToFileSystemFailed;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.EmptyMuscleGroupsRequest;
import com.zihler.fitness_tracker.domain.exceptions.InvalidTitleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class ErrorHandling {
    private static final Logger logger = LogManager.getLogger();

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorViewModel> handleGenericException(Exception bad) {
        logger.error(bad.toString());
        bad.printStackTrace();
        return status(I_AM_A_TEAPOT).body(new ErrorViewModel(Arrays.toString(bad.getStackTrace()) + ", " + bad.getMessage()));
    }

    @ExceptionHandler(value = InvalidTitleException.class)
    public ResponseEntity<ErrorViewModel> handleInvalidTitleException(Exception bad) {
        return status(BAD_REQUEST).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = ConfiguringFileSystemFailed.class)
    public ResponseEntity<ErrorViewModel> handleConfiguringFileSystemFailed(Exception bad) {
        return status(INTERNAL_SERVER_ERROR).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = LoadingDataFromFileSystemFailed.class)
    public ResponseEntity<ErrorViewModel> handleLoadingDataFromFileSystemFailed(Exception bad) {
        return status(INTERNAL_SERVER_ERROR).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = StoringToFileSystemFailed.class)
    public ResponseEntity<ErrorViewModel> handleStoringMuscleGroupsAndExercisesToFileSystemFailed(Exception bad) {
        return status(INTERNAL_SERVER_ERROR).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = EmptyMuscleGroupsRequest.class)
    public ResponseEntity<ErrorViewModel> handleEmptyMuscleGroupsRequest(Exception bad) {
        return status(BAD_REQUEST).body(new ErrorViewModel(bad.getMessage()));
    }
}
