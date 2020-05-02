package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class WorkoutIdInput {
    private WorkoutViewModel workoutId;

    public WorkoutIdInput(WorkoutViewModel workoutId) {
        this.workoutId = workoutId;
    }

    public WorkoutId workoutId() {
        return WorkoutId.of(workoutId.getWorkoutId());
    }
    
}
