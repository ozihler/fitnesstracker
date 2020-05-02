package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullWorkoutViewModel;

import java.util.List;

public class FullWorkoutsViewModel {
    @JsonProperty("workouts")
    private List<FullWorkoutViewModel> workouts;

    public FullWorkoutsViewModel(List<FullWorkoutViewModel> workouts) {
        this.workouts = workouts;
    }

    public FullWorkoutsViewModel() {
    }

    public List<FullWorkoutViewModel> getWorkouts() {
        return workouts;
    }
}
