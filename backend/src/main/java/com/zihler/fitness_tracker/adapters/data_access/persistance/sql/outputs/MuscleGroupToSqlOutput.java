package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;

public class MuscleGroupToSqlOutput {
    private final MuscleGroup muscleGroup;

    public MuscleGroupToSqlOutput(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public MuscleGroupRow getRow() {
        var muscleGroupRow = new MuscleGroupRow();
        muscleGroupRow.setName(muscleGroup.getName().toString());
        muscleGroupRow.setSelectable(muscleGroup.isSelectable());
        return muscleGroupRow;
    }
}
