package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading.MuscleGroupJson;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;

public class MuscleGroupRowOutput {
    private final MuscleGroupJson muscleGroupJson;

    public MuscleGroupRowOutput(MuscleGroupJson muscleGroupJson) {
        this.muscleGroupJson = muscleGroupJson;
    }

    public MuscleGroupRow getRow() {
        var muscleGroupRow = new MuscleGroupRow();
        muscleGroupRow.setName(muscleGroupJson.getName());
        muscleGroupRow.setSelectable(muscleGroupJson.isSelectable());
        muscleGroupRow.setExercises(new ExercisesRowOutput(muscleGroupJson.getExercises()).getRows());
        return muscleGroupRow;
    }
}
