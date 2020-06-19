package com.zihler.fitness_tracker.domain.exceptions;

public class WorkoutIdNotFoundException extends IllegalArgumentException {
    public WorkoutIdNotFoundException(String message) {
        super(message);
    }
}
