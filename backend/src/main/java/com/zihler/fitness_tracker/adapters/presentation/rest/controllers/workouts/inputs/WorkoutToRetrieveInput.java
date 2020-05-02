package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class WorkoutToRetrieveInput {
    private String workoutId;

    public WorkoutToRetrieveInput(String workoutId) {
        this.workoutId = workoutId;
    }

    public WorkoutId workoutId() {
        return WorkoutId.of(workoutId);
    }
}
