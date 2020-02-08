package com.zihler.fitness_tracker.application.use_cases.exercises.create_exercises.exceptions;

public class NoExercisesToCreateException extends RuntimeException {
    private static final long serialVersionUID = 286668356548021471L;

    public NoExercisesToCreateException() {
        super("No exercises passed to create");
    }
}
