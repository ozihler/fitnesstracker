package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;
import com.zihler.fitness_tracker.domain.entities.Exercise;

public class ExerciseRowInput {
    private final Exercise exercise;

    public ExerciseRowInput(Exercise exercise) {
        this.exercise = exercise;
    }


    public ExerciseRow getRow() {
        var row = new ExerciseRow();
        return row;
    }
}
