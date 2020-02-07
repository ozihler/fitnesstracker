package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullWorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs.FullMuscleGroupsOutput;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;

import java.time.Clock;
import java.time.ZonedDateTime;

public class FullWorkoutOutput {
    private WorkoutDocument document;

    public FullWorkoutOutput(WorkoutDocument document) {
        this.document = document;
    }

    public FullWorkoutViewModel toViewModel() {
        return new FullWorkoutViewModel(
                document.getWorkoutId().toLong(),
                formatCreationTime(document.getCreationDateTime()),
                document.getWorkoutTitle().toString(),
                new FullMuscleGroupsOutput(document.getMuscleGroups()).toViewModel()
        );
    }

    private static long formatCreationTime(ZonedDateTime creationDateTime) {
        return creationDateTime.withZoneSameInstant(Clock.systemDefaultZone().getZone()).toInstant().toEpochMilli();
    }

}