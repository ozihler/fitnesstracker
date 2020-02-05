package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupNameViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;

public class MuscleGroupOutput {

    private MuscleGroupDocument muscleGroup;

    public MuscleGroupOutput(MuscleGroupDocument muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public MuscleGroupNameViewModel toViewModel() {
        return new MuscleGroupNameViewModel(muscleGroup.getName().toString());
    }
}
