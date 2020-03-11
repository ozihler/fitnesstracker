package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.ExerciseRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.values.Exercise;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExercisesToDBOutput {
    private final List<Exercise> exercises;
    private final MuscleGroupRow muscleGroupRow;

    public ExercisesToDBOutput(List<Exercise> exercises, MuscleGroupRow muscleGroupRow) {
        this.exercises = exercises;
        this.muscleGroupRow = muscleGroupRow;
    }

    public List<ExerciseRow> exercises() {
        return exercises.stream()
                .map(exercise -> new ExerciseToDBOutput(exercise, muscleGroupRow))
                .map(ExerciseToDBOutput::exerciseRow)
                .collect(toList());
    }
}
