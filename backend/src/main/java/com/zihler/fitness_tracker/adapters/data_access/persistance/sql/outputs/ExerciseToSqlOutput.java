package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.entities.Exercise;

public class ExerciseToSqlOutput {
    private final Exercise exercise;
    private final MuscleGroupRow muscleGroupRowToAppendExercises;

    public ExerciseToSqlOutput(Exercise exercise, MuscleGroupRow muscleGroupRowToAppendExercises) {
        this.exercise = exercise;
        this.muscleGroupRowToAppendExercises = muscleGroupRowToAppendExercises;
    }

    public ExerciseRow getRow() {
        var row = new ExerciseRow();
        row.setName(exercise.getName().toString());
        row.setSelectable(exercise.isSelectable());
        row.setMuscleGroup(muscleGroupRowToAppendExercises);
        row.setMultiplier(exercise.getMultiplier().value());

        // todo no sets added here (?)
        return row;
    }
}
