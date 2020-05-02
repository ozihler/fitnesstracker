package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;

public class WorkoutToUpdate {
    @JsonProperty("workout")
    private WorkoutViewModel workout;

    public WorkoutToUpdate(WorkoutViewModel workout) {
        this.workout = workout;
    }

    public WorkoutToUpdate() {
    }

    public WorkoutViewModel getWorkout() {
        return workout;
    }
}
