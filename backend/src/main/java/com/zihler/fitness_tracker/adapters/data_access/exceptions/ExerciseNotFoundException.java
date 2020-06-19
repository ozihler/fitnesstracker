package com.zihler.fitness_tracker.adapters.data_access.exceptions;

public class ExerciseNotFoundException extends IllegalArgumentException {
    public ExerciseNotFoundException(String message) {
        super(message);

    }
}
