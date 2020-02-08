package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs;

import com.zihler.fitness_tracker.domain.values.MuscleGroupName;

public class MuscleGroupNameInput {
    private String muscleGroupName;

    public MuscleGroupNameInput(String muscleGroupName) {
        this.muscleGroupName = muscleGroupName;
    }

    public MuscleGroupName muscleGroupName() {
        return MuscleGroupName.of(muscleGroupName);
    }
}
