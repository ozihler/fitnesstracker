package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.rowtypes.ExerciseRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.rowtypes.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;

import java.util.List;

public class MuscleGroupToDBOutput {
    private MuscleGroup muscleGroup;

    public MuscleGroupToDBOutput(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public MuscleGroupRow muscleGroupRow() {
        MuscleGroupRow muscleGroupRow = new MuscleGroupRow();

        muscleGroupRow.setName(muscleGroup.getName().toString());
        muscleGroupRow.setSelectable(muscleGroup.isSelectable());
        muscleGroupRow.setExercises(exercises(muscleGroupRow));

        return muscleGroupRow;
    }

    private List<ExerciseRow> exercises(MuscleGroupRow muscleGroupRow) {
        return new ExercisesToDBOutput(muscleGroup.getExercises().getExercises(), muscleGroupRow).exercises();
    }

}
