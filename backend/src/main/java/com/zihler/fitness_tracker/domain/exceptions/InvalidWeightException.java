package com.zihler.fitness_tracker.domain.exceptions;

public class InvalidWeightException extends RuntimeException {
    public InvalidWeightException(String message) {
        super(message);
    }
}
