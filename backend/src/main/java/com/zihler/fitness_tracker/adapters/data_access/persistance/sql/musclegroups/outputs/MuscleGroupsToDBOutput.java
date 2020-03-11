package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.List;
import java.util.stream.Collectors;

public class MuscleGroupsToDBOutput {
    private MuscleGroups muscleGroups;

    public MuscleGroupsToDBOutput(MuscleGroups muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public List<MuscleGroupRow> muscleGroupRows() {
        return this.muscleGroups
                .getMuscleGroups()
                .stream()
                .map(MuscleGroupToDBOutput::new)
                .map(MuscleGroupToDBOutput::muscleGroupRow)
                .collect(Collectors.toList());
    }
}
