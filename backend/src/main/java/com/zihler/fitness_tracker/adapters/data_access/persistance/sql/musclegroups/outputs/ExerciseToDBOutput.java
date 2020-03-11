package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.ExerciseRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;

public class ExerciseToDBOutput {

    private Exercise exercise;
    private MuscleGroupRow muscleGroupRow;

    public ExerciseToDBOutput(Exercise exercise, MuscleGroupRow muscleGroupRow) {
        this.exercise = exercise;
        this.muscleGroupRow = muscleGroupRow;
    }

    public ExerciseRow exerciseRow() {
        ExerciseRow exerciseRow = new ExerciseRow();
        exerciseRow.setName(exercise.getName().toString());
        exerciseRow.setSelectable(exercise.isSelectable());
        exerciseRow.setMuscleGroup(muscleGroupRow);
        return exerciseRow;
    }
}
