package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;

public class MuscleGroupFromSqlInput {
    private final MuscleGroupRow muscleGroupRow;

    public MuscleGroupFromSqlInput(MuscleGroupRow muscleGroupRow) {
        this.muscleGroupRow = muscleGroupRow;
    }

    public MuscleGroup getMuscleGroup() {
        var muscleGroup = new MuscleGroup(
                Name.of(muscleGroupRow.getName()),
                new ExercisesFromSqlInput(muscleGroupRow.getExercises()).getExercises());

        muscleGroup.setSelectable(muscleGroupRow.isSelectable());
        return muscleGroup;
    }
}
