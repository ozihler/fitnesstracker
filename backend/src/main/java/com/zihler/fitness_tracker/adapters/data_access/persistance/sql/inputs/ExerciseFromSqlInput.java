package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.values.Multiplier;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Sets;

public class ExerciseFromSqlInput {
    private final ExerciseRow exerciseRow;

    public ExerciseFromSqlInput(ExerciseRow exerciseRow) {
        this.exerciseRow = exerciseRow;
    }

    public Exercise getExercise() {
        var exercise = new Exercise(
                Name.of(exerciseRow.getName()),
                Sets.empty(),
                Multiplier.of(exerciseRow.getMultiplier())
        );

        exercise.setSelectable(exerciseRow.isSelectable());
        return exercise;
    }
}
