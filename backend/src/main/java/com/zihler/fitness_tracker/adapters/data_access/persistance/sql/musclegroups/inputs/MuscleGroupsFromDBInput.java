package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.rowtypes.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupsFromDBInput {
    private List<MuscleGroupRow> muscleGroupRows;

    public MuscleGroupsFromDBInput(List<MuscleGroupRow> muscleGroupRows) {
        this.muscleGroupRows = muscleGroupRows;
    }

    public MuscleGroups muscleGroups() {
        return MuscleGroups.of(muscleGroupRows
                .stream()
                .map(MuscleGroupFromDBInput::new)
                .map(MuscleGroupFromDBInput::muscleGroup)
                .filter(MuscleGroup::isSelectable)
                .collect(toList()));
    }
}
