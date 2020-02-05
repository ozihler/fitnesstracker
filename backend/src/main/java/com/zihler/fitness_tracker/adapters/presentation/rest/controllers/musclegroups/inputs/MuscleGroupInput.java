package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.domain.values.Name;

public class MuscleGroupInput {
    private String muscleGroupName;

    public MuscleGroupInput(String muscleGroupName) {
        this.muscleGroupName = muscleGroupName;
    }

    public MuscleGroupDocument muscleGroup() {
        return MuscleGroupDocument.of(Name.of(muscleGroupName));
    }
}
