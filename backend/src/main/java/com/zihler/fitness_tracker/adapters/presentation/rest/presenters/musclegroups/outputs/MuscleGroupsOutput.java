package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupNameViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MuscleGroupsOutput {
    private MuscleGroupsDocument muscleGroupsDocument;

    public MuscleGroupsOutput(MuscleGroupsDocument muscleGroupsDocument) {
        this.muscleGroupsDocument = muscleGroupsDocument;
    }

    public MuscleGroupsViewModel toViewModel() {
        return new MuscleGroupsViewModel(toViewModels());
    }

    private Set<MuscleGroupNameViewModel> toViewModels() {
        return muscleGroupsDocument.getMuscleGroups()
                .stream()
                .map(MuscleGroupOutput::new)
                .map(MuscleGroupOutput::toViewModel)
                .collect(toSet());
    }
}
