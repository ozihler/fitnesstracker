package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.CreateMuscleGroupsRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.EmptyMuscleGroupsRequest;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.domain.values.Names;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MuscleGroupsInput {
    private CreateMuscleGroupsRequest request;

    public MuscleGroupsInput(CreateMuscleGroupsRequest request) {
        this.request = request;
    }

    public MuscleGroupsDocument muscleGroups() {
        String muscleGroupNames = request.getMuscleGroupNames();
        if (muscleGroupNames == null || muscleGroupNames.isBlank()) {
            throw new EmptyMuscleGroupsRequest();
        }

        Set<MuscleGroupDocument> documents = toDocuments(muscleGroupNames);

        return MuscleGroupsDocument.of(documents);
    }

    private Set<MuscleGroupDocument> toDocuments(String muscleGroupNames) {
        return Names.in(muscleGroupNames)
                .values()
                .stream()
                .map(MuscleGroupDocument::of)
                .collect(toSet());
    }

}
