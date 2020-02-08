package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs;

import com.zihler.fitness_tracker.domain.values.ExerciseName;

public class ExerciseNameInput {
    private String exerciseName;

    public ExerciseNameInput(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public ExerciseName exerciseName() {
        return ExerciseName.of(exerciseName);
    }

}
