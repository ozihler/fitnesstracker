package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkoutIdViewModel {
    @JsonProperty("workoutId")
    private String workoutId;

    public WorkoutIdViewModel() {
    }

    public WorkoutIdViewModel(String workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutId() {
        return workoutId;
    }
}
