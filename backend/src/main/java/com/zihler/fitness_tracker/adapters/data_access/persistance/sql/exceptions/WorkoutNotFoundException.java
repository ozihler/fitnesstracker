package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions;

public class WorkoutNotFoundException extends RuntimeException {
    public WorkoutNotFoundException(String message) {
        super(message);
    }
}
