package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.errors;

public class ErrorViewModel {
    private String message;

    public ErrorViewModel(String message) {
        this.message = message;
    }

    public ErrorViewModel() {
    }

    public String getMessage() {
        return message;
    }
}
