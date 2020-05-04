package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.domain.values.Name;

public class MuscleGroupNameOutput {
    private Name name;

    public MuscleGroupNameOutput(Name name) {
        this.name = name;
    }

    public MuscleGroupViewModel toViewModel() {
        return new MuscleGroupViewModel(name.toString());
    }
}
