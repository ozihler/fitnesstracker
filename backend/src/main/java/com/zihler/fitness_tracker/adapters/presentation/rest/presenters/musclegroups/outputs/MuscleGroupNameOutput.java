package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupNameViewModel;
import com.zihler.fitness_tracker.domain.values.Name;

public class MuscleGroupNameOutput {
    private Name name; // todo make MuscleGroupName

    public MuscleGroupNameOutput(Name name) {
        this.name = name;
    }

    public MuscleGroupNameViewModel toViewModel() {
        return new MuscleGroupNameViewModel(name.toString());
    }
}
