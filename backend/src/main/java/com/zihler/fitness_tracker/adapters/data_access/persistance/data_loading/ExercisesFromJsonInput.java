package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.zihler.fitness_tracker.domain.values.Exercises;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExercisesFromJsonInput {
    private final List<ExerciseJson> jsons;

    public ExercisesFromJsonInput(List<ExerciseJson> jsons) {
        this.jsons = jsons;
    }

    public Exercises getExercises() {
        return Exercises.of(
                jsons.stream().map(ExerciseFromJsonInput::new)
                        .map(ExerciseFromJsonInput::getExercise)
                        .collect(toList())
        );
    }
}
