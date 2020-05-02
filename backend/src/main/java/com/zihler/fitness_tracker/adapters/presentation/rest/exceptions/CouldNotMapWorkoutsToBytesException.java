package com.zihler.fitness_tracker.adapters.presentation.rest.exceptions;

public class CouldNotMapWorkoutsToBytesException extends RuntimeException {
    public CouldNotMapWorkoutsToBytesException(Exception e) {
        super(e);
    }
}
