package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkoutToCreateRequest {
    @JsonProperty("title")
    private String title;

    public WorkoutToCreateRequest() {
    }

    public WorkoutToCreateRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
