package com.zihler.fitness_tracker.domain.exceptions;

public class InvalidTitleException extends RuntimeException {
    public InvalidTitleException(String message) {
        super(message);
    }
}
