package com.zihler.fitness_tracker.application.use_cases.create_exercises.exceptions;

public class NoExercisesToCreateException extends RuntimeException {
    public NoExercisesToCreateException() {
        super("No exercises passed to create");
    }
}
