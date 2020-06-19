package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupsToSqlOutput {
    private final MuscleGroups muscleGroups;

    public MuscleGroupsToSqlOutput(MuscleGroups muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public List<MuscleGroupRow> getRows() {
        return muscleGroups.getMuscleGroups().stream()
                .map(MuscleGroupToSqlOutput::new)
                .map(MuscleGroupToSqlOutput::getRow)
                .collect(toList());
    }
}
