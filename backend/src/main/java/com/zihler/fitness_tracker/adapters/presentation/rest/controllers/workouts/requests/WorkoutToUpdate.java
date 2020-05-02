package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullWorkoutViewModel;

public class WorkoutToUpdate {
    @JsonProperty("workout")
    private FullWorkoutViewModel workout;

    public WorkoutToUpdate(FullWorkoutViewModel workout) {
        this.workout = workout;
    }

    public WorkoutToUpdate() {
    }

    public FullWorkoutViewModel getWorkout() {
        return workout;
    }
}
