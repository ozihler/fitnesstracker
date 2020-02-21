package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutIdViewModel;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class WorkoutIdInput {
    private WorkoutIdViewModel workoutId;

    public WorkoutIdInput(WorkoutIdViewModel workoutId) {
        this.workoutId = workoutId;
    }

    public WorkoutId workoutId() {
        return WorkoutId.of(workoutId.getWorkoutId());
    }
    
}
