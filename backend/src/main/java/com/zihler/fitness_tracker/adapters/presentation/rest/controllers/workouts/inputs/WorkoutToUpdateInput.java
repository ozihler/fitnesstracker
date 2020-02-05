package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.MuscleGroupFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WorkoutToUpdateInput {
    private WorkoutToUpdate request;

    public WorkoutToUpdateInput(WorkoutToUpdate request) {
        this.request = request;
    }

    public WorkoutDocument workout() {
        WorkoutFullViewModel workout = request.getWorkout();

        return new WorkoutDocument(
                WorkoutId.of(workout.getGid()),
                ZonedDateTime.from(Instant.ofEpochMilli(workout.getCreationDate()).atZone(Clock.systemDefaultZone().getZone())),
                WorkoutTitle.of(workout.getTitle()),
                toDocument(workout.getMuscleGroups())
        );
    }

    private static MuscleGroupsDocument toDocument(Set<MuscleGroupFullViewModel> muscleGroups) {
        return MuscleGroupsDocument.of(muscleGroups
                .stream()
                .map(MuscleGroupFullViewModel::getName)
                .map(Name::of)
                .map(MuscleGroupDocument::of)
                .collect(toSet()));
    }

}
