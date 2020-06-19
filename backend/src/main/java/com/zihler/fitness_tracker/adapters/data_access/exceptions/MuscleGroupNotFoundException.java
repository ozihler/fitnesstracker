package com.zihler.fitness_tracker.adapters.data_access.exceptions;

public class MuscleGroupNotFoundException extends IllegalArgumentException {
    public MuscleGroupNotFoundException(String message) {
        super(message);
    }
}
