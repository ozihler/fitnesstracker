package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FullMuscleGroupsOutput {
    private MuscleGroupsDocument document;

    public FullMuscleGroupsOutput(MuscleGroupsDocument document) {
        this.document = document;
    }


    public List<MuscleGroupViewModel> toViewModel() {
        return document.getMuscleGroups()
                .stream()
                .map(FullMuscleGroupOutput::new)
                .map(FullMuscleGroupOutput::toViewModel)
                .collect(toList());
    }

}
