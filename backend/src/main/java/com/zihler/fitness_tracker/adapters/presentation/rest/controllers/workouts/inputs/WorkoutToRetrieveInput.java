package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class WorkoutToRetrieveInput {
    private String workoutGid;

    public WorkoutToRetrieveInput(String workoutGid) {
        this.workoutGid = workoutGid;
    }

    public WorkoutId workoutGid() {
        return WorkoutId.of(Long.parseLong(workoutGid));
    }
}
