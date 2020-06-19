package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupsFromJsonInput {
    private final List<MuscleGroupJson> json;

    public MuscleGroupsFromJsonInput(List<MuscleGroupJson> json) {
        this.json = json;
    }

    public MuscleGroups getMuscleGroups() {
        return MuscleGroups.of(
                json.stream()
                        .map(MuscleGroupFromJsonInput::new)
                        .map(MuscleGroupFromJsonInput::getMuscleGroup)
                        .collect(toList())
        );
    }
}
