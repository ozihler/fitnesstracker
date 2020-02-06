package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.MuscleGroupFullViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MuscleGroupsFullOutput {
    private MuscleGroupsDocument document;

    public MuscleGroupsFullOutput(MuscleGroupsDocument document) {
        this.document = document;
    }


    public Set<MuscleGroupFullViewModel> toViewModel() {
        return document.getMuscleGroups()
                .stream()
                .map(MuscleGroupFullOutput::new)
                .map(MuscleGroupFullOutput::toViewModel)
                .collect(toSet());
    }

}
