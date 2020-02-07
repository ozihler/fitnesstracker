package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullMuscleGroupViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class FullMuscleGroupsOutput {
    private MuscleGroupsDocument document;

    public FullMuscleGroupsOutput(MuscleGroupsDocument document) {
        this.document = document;
    }


    public Set<FullMuscleGroupViewModel> toViewModel() {
        return document.getMuscleGroups()
                .stream()
                .map(FullMuscleGroupOutput::new)
                .map(FullMuscleGroupOutput::toViewModel)
                .collect(toSet());
    }

}
