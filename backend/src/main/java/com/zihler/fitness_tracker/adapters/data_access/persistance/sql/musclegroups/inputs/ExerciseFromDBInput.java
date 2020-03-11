package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.ExerciseRow;
import com.zihler.fitness_tracker.domain.values.Exercise;

public class ExerciseFromDBInput {
    private ExerciseRow exerciseRow;

    public ExerciseFromDBInput(ExerciseRow exerciseRow) {
        this.exerciseRow = exerciseRow;
    }


    public Exercise exercise() {
        return Exercise.of(exerciseRow.getName());
    }
}
