package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutIdRequest;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class WorkoutIdInput {
    private final WorkoutIdRequest workoutId;

    public WorkoutIdInput(WorkoutIdRequest workoutId) {
        this.workoutId = workoutId;
    }

    public WorkoutId workoutId() {
        return WorkoutId.of(workoutId.getWorkoutId());
    }
    
}
