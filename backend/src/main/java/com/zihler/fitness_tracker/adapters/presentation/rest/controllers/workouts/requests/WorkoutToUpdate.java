package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkoutToUpdate {
    @JsonProperty("workout")
    private WorkoutFullViewModel workout;

    public WorkoutToUpdate(WorkoutFullViewModel workout) {
        this.workout = workout;
    }

    public WorkoutToUpdate() {
    }

    public WorkoutFullViewModel getWorkout() {
        return workout;
    }
}
