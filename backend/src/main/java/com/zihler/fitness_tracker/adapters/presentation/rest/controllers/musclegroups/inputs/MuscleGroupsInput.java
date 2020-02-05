package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.CreateMuscleGroupsRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.EmptyMuscleGroupsRequest;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.domain.values.Name;
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
        return names(muscleGroupNames)
                .stream()
                .map(MuscleGroupInput::new)
                .map(MuscleGroupInput::muscleGroup)
                .collect(toSet());
    }

    private Set<String> names(String muscleGroupNames) {
        // todo find better solution for this (Names creates name objects,
        //  then converts them to strings again,
        //  and then converts them in MuscleGroupInput to names again)
        return Names.in(muscleGroupNames)
                .values()
                .stream()
                .map(Name::toString)
                .collect(toSet());
    }

}
