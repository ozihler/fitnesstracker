package com.zihler.fitness_tracker.domain.exceptions;

public class IllegalNameException extends IllegalArgumentException {
    public IllegalNameException(String message) {
        super(message);
    }
}
