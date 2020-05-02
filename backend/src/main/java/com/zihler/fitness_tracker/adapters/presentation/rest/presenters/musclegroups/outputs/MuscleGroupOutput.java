package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;

public class MuscleGroupOutput {

    private MuscleGroupDocument muscleGroup;

    public MuscleGroupOutput(MuscleGroupDocument muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public MuscleGroupViewModel toViewModel() {
        return new MuscleGroupViewModel(muscleGroup.getName().toString());
    }
}
