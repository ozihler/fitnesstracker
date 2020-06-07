package com.zihler.fitness_tracker.domain.exceptions;

public class IllegalRepetitionsException extends IllegalArgumentException {
    public IllegalRepetitionsException(String message) {
        super(message);
    }
}
