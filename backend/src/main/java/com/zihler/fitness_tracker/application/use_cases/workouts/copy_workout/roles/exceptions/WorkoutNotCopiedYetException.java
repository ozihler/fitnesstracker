package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles.exceptions;

public class WorkoutNotCopiedYetException extends RuntimeException {
    public WorkoutNotCopiedYetException(String message) {
        super(message);
    }
}
