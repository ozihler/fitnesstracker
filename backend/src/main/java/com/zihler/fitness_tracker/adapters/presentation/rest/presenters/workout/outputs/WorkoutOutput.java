package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupNameViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutAndMuscleGroupNamesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WorkoutOutput {
    private WorkoutDocument workoutDocument;

    public WorkoutOutput(WorkoutDocument workoutDocument) {
        this.workoutDocument = workoutDocument;
    }

    static long formatCreationTime(ZonedDateTime creationDateTime) {
        return creationDateTime.withZoneSameInstant(Clock.systemDefaultZone().getZone()).toInstant().toEpochMilli();
    }

    public WorkoutAndMuscleGroupNamesViewModel toViewModel() {
        return new WorkoutAndMuscleGroupNamesViewModel(
                workoutDocument.getWorkoutId().toLong(),
                formatCreationTime(workoutDocument.getCreationDateTime()),
                workoutDocument.getWorkoutTitle().toString(),
                toViewModel(workoutDocument.getMuscleGroups())
        );
    }

    private Set<MuscleGroupNameViewModel> toViewModel(MuscleGroupsDocument muscleGroups) {
        return muscleGroups.getMuscleGroups().
                stream()
                .map(m -> new MuscleGroupNameViewModel(m.getName().toString()))
                .collect(toSet());
    }
}
