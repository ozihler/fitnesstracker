package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading.MuscleGroupJson;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupsRowOutput {
    private final List<MuscleGroupJson> muscleGroups;

    public MuscleGroupsRowOutput(List<MuscleGroupJson> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public List<MuscleGroupRow> getRows() {
        return muscleGroups.stream()
                .map(MuscleGroupRowOutput::new)
                .map(MuscleGroupRowOutput::getRow)
                .collect(toList());
    }
}
