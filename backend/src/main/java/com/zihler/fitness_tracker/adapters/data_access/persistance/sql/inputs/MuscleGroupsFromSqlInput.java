package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.List;
import java.util.stream.Collectors;

public class MuscleGroupsFromSqlInput {
    private final List<MuscleGroupRow> rows;

    public MuscleGroupsFromSqlInput(List<MuscleGroupRow> rows) {
        this.rows = rows;
    }

    public MuscleGroups getMuscleGroups() {
        return new MuscleGroups(
                rows.stream()
                        .map(MuscleGroupFromSqlInput::new)
                        .map(MuscleGroupFromSqlInput::getMuscleGroup)
                        .collect(Collectors.toList()));
    }
}
