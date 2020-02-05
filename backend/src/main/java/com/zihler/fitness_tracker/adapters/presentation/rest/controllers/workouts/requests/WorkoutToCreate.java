package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkoutToCreate {
    @JsonProperty("title")
    private String title;

    public WorkoutToCreate() {
    }

    public WorkoutToCreate(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
