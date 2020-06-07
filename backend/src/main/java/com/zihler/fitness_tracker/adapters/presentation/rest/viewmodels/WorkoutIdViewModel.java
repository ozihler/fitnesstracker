package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

public class WorkoutIdViewModel {
    private String workoutId;

    public WorkoutIdViewModel(String workoutId) {
        this.workoutId = workoutId;
    }

    public WorkoutIdViewModel() {
    }

    public String getWorkoutId() {
        return workoutId;
    }
}
