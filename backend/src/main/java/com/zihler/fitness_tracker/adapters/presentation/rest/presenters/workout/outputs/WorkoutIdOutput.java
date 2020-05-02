package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class WorkoutIdOutput {
    private WorkoutId workoutId;

    public WorkoutIdOutput(WorkoutId workoutId) {
        this.workoutId = workoutId;
    }

    public WorkoutViewModel toViewModel() {
        return new WorkoutViewModel(workoutId.toString());
    }
}
