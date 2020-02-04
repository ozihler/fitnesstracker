package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WorkoutsInOverviewViewModel {
    @JsonProperty("workouts")
    private List<WorkoutInOverviewViewModel> workouts;

    public WorkoutsInOverviewViewModel(List<WorkoutInOverviewViewModel> workouts) {
        this.workouts = workouts;
    }

    public WorkoutsInOverviewViewModel() {
    }

    public List<WorkoutInOverviewViewModel> getWorkouts() {
        return workouts;
    }
}
