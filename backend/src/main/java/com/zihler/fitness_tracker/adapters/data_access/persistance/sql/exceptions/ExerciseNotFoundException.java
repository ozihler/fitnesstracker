package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions;

public class ExerciseNotFoundException extends RuntimeException {
    public ExerciseNotFoundException(String message) {
        super(message);
    }
}
