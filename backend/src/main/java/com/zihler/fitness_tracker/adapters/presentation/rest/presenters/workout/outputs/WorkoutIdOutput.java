package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutIdViewModel;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class WorkoutIdOutput {
    private WorkoutId workoutId;

    public WorkoutIdOutput(WorkoutId workoutId) {
        this.workoutId = workoutId;
    }

    public WorkoutIdViewModel toViewModel() {
        return new WorkoutIdViewModel(workoutId.toString());
    }
}
