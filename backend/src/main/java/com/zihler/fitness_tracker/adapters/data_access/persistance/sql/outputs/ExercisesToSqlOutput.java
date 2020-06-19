package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.entities.Exercise;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExercisesToSqlOutput {
    private final MuscleGroupRow muscleGroupRowToAppendExercises;
    private final List<Exercise> exercises;

    public ExercisesToSqlOutput(MuscleGroupRow muscleGroupRowToAppendExercises, List<Exercise> exercises) {
        this.muscleGroupRowToAppendExercises = muscleGroupRowToAppendExercises;
        this.exercises = exercises;
    }

    public List<ExerciseRow> getRows() {
        return exercises.stream()
                .map(exercise -> new ExerciseToSqlOutput(exercise, muscleGroupRowToAppendExercises))
                .map(ExerciseToSqlOutput::getRow)
                .collect(toList());
    }
}
