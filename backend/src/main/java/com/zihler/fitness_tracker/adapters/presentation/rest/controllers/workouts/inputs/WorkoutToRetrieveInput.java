package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.domain.values.GID;

public class WorkoutToRetrieveInput {
    private String workoutGid;

    public WorkoutToRetrieveInput(String workoutGid) {
        this.workoutGid = workoutGid;
    }

    public GID workoutGid() {
        return GID.of(Long.parseLong(workoutGid));
    }
}
