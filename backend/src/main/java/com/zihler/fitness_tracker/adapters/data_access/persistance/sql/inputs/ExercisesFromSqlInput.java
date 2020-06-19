package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;
import com.zihler.fitness_tracker.domain.values.Exercises;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExercisesFromSqlInput {
    private final List<ExerciseRow> exercises;

    public ExercisesFromSqlInput(List<ExerciseRow> exercises) {
        this.exercises = exercises;
    }

    public Exercises getExercises() {
        return Exercises.of(
                exercises.stream()
                        .map(ExerciseFromSqlInput::new)
                        .map(ExerciseFromSqlInput::getExercise)
                        .collect(toList())
        );
    }
}
