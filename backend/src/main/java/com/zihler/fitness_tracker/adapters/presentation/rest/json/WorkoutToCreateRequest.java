package com.zihler.fitness_tracker.adapters.presentation.rest.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkoutToCreateRequest {
    @JsonProperty("title")
    private String title;

    public String getTitle() {
        return title;
    }
}
