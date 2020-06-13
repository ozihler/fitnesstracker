package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exceptions;

public class ResponseUndefinedException extends IllegalStateException {
    public ResponseUndefinedException(String message) {
        super(message);
    }
}
