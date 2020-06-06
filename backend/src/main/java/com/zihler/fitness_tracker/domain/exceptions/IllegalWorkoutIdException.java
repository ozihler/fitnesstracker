package com.zihler.fitness_tracker.domain.exceptions;

public class IllegalWorkoutIdException extends IllegalArgumentException {
    public IllegalWorkoutIdException(String message) {
        super(message);
    }
}
