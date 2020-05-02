package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupsOutput {
    private MuscleGroupsDocument muscleGroupsDocument;

    public MuscleGroupsOutput(MuscleGroupsDocument muscleGroupsDocument) {
        this.muscleGroupsDocument = muscleGroupsDocument;
    }

    public MuscleGroupsViewModel toViewModel() {
        return new MuscleGroupsViewModel(toViewModels());
    }

    private List<MuscleGroupViewModel> toViewModels() {
        return muscleGroupsDocument.getMuscleGroups()
                .stream()
                .map(MuscleGroupOutput::new)
                .map(MuscleGroupOutput::toViewModel)
                .collect(toList());
    }
}
