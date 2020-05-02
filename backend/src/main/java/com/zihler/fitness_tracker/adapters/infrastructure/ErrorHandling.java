package com.zihler.fitness_tracker.adapters.infrastructure;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.ConfiguringFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.CouldNotDeleteFolderException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.LoadingDataFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.StoringToFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions.ExerciseNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions.MuscleGroupNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions.WorkoutNotFoundException;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.EmptyMuscleGroupsRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.exceptions.CouldNotMapWorkoutsToBytesException;
import com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles.exceptions.WorkoutNotCopiedYetException;
import com.zihler.fitness_tracker.domain.exceptions.InvalidTitleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class ErrorHandling {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandling.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorViewModel> handleGenericException(Exception bad) {
        log(bad);
        return status(I_AM_A_TEAPOT).body(new ErrorViewModel(Arrays.toString(bad.getStackTrace()) + ", " + bad.getMessage()));
    }
    @ExceptionHandler(value = CouldNotMapWorkoutsToBytesException.class)
    public ResponseEntity<ErrorViewModel> handleCouldNotMapWorkoutsToBytesException(CouldNotMapWorkoutsToBytesException bad) {
        log(bad);
        return status(INTERNAL_SERVER_ERROR).body(new ErrorViewModel(Arrays.toString(bad.getStackTrace()) + ", " + bad.getMessage()));
    }

    @ExceptionHandler(value = InvalidTitleException.class)
    public ResponseEntity<ErrorViewModel> handleInvalidTitleException(Exception bad) {
        log(bad);
        return status(BAD_REQUEST).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = ConfiguringFileSystemFailed.class)
    public ResponseEntity<ErrorViewModel> handleConfiguringFileSystemFailed(Exception bad) {
        log(bad);
        return status(INTERNAL_SERVER_ERROR).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = CouldNotDeleteFolderException.class)
    public ResponseEntity<ErrorViewModel> handleCouldNotDeleteFolderException(Exception bad) {
        log(bad);
        return status(INTERNAL_SERVER_ERROR).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = LoadingDataFromFileSystemFailed.class)
    public ResponseEntity<ErrorViewModel> handleLoadingDataFromFileSystemFailed(Exception bad) {
        log(bad);
        return status(INTERNAL_SERVER_ERROR).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = StoringToFileSystemFailed.class)
    public ResponseEntity<ErrorViewModel> handleStoringMuscleGroupsAndExercisesToFileSystemFailed(Exception bad) {
        log(bad);
        return status(INTERNAL_SERVER_ERROR).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = WorkoutNotCopiedYetException.class)
    public ResponseEntity<ErrorViewModel> handleWorkoutNotCopiedYetException(Exception bad) {
        log(bad);
        return status(INTERNAL_SERVER_ERROR).body(new ErrorViewModel(bad.getMessage()));
    }

    @ExceptionHandler(value = EmptyMuscleGroupsRequest.class)
    public ResponseEntity<ErrorViewModel> handleEmptyMuscleGroupsRequest(Exception bad) {
        log(bad);
        return status(BAD_REQUEST).body(new ErrorViewModel(bad.getMessage()));
    }
    @ExceptionHandler(value = ExerciseNotFoundException.class)
    public ResponseEntity<ErrorViewModel> handleExerciseNotFoundException(Exception bad) {
        log(bad);
        return status(NOT_FOUND).body(new ErrorViewModel(bad.getMessage()));
    }
    @ExceptionHandler(value = MuscleGroupNotFoundException.class)
    public ResponseEntity<ErrorViewModel> handleMuscleGroupNotFoundException(Exception bad) {
        log(bad);
        return status(NOT_FOUND).body(new ErrorViewModel(bad.getMessage()));
    }
    @ExceptionHandler(value = WorkoutNotFoundException.class)
    public ResponseEntity<ErrorViewModel> handleWorkoutNotFoundException(Exception bad) {
        log(bad);
        return status(NOT_FOUND).body(new ErrorViewModel(bad.getMessage()));
    }

    private void log(Exception bad) {
        logger.error("An exception was thrown. ", bad);
    }
}
