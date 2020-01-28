package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MuscleGroupsOutput {
    private MuscleGroupsDocument muscleGroupsDocument;

    public MuscleGroupsOutput(MuscleGroupsDocument muscleGroupsDocument) {
        this.muscleGroupsDocument = muscleGroupsDocument;
    }

    public MuscleGroupsViewModel toViewModel() {
        return new MuscleGroupsViewModel(toViewModels(muscleGroupsDocument.getMuscleGroups()));
    }

    private Set<MuscleGroupViewModel> toViewModels(Set<MuscleGroupDocument> muscleGroups) {
        return muscleGroups.stream()
                .map(document -> new MuscleGroupViewModel(document.getName().toString()))
                .collect(toSet());
    }
}
