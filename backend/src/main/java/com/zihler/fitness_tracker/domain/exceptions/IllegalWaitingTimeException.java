package com.zihler.fitness_tracker.domain.exceptions;

public class IllegalWaitingTimeException extends IllegalArgumentException {
    public IllegalWaitingTimeException(String message) {
        super(message);
    }
}
