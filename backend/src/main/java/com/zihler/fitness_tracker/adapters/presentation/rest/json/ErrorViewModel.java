package com.zihler.fitness_tracker.adapters.presentation.rest.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorViewModel {
    @JsonProperty("message")
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
