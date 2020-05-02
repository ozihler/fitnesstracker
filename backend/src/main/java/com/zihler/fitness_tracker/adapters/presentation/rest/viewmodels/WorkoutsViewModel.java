package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WorkoutsViewModel {
    @JsonProperty("workouts")
    private List<WorkoutViewModel> workouts;

    public WorkoutsViewModel(List<WorkoutViewModel> workouts) {
        this.workouts = workouts;
    }

    public WorkoutsViewModel() {
    }

    public List<WorkoutViewModel> getWorkouts() {
        return workouts;
    }
}
