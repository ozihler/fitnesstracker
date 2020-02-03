package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.domain.values.GID;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WorkoutToUpdateInput {
    private WorkoutViewModel request;

    public WorkoutToUpdateInput(WorkoutViewModel request) {
        this.request = request;
    }

    public WorkoutDocument workout() {
        return new WorkoutDocument(
                GID.of(request.getGid()),
                ZonedDateTime.from(Instant.ofEpochMilli(request.getCreationDate()).atZone(Clock.systemDefaultZone().getZone())),
                WorkoutTitle.from(request.getTitle()),
                toDocument(request.getMuscleGroups())
        );
    }

    private static MuscleGroupsDocument toDocument(Set<MuscleGroupViewModel> muscleGroups) {
        return MuscleGroupsDocument.of(muscleGroups
                .stream()
                .map(MuscleGroupViewModel::getName)
                .map(Name::of)
                .map(MuscleGroupDocument::of)
                .collect(toSet()));
    }

}
