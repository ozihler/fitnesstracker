package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs;

import com.zihler.fitness_tracker.domain.values.Name;

public class ExerciseNameInput {
    private String exerciseName;

    public ExerciseNameInput(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Name exerciseName() {
        return Name.of(exerciseName);
    }

}
