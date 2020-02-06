package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs.MuscleGroupsFullOutput;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;

import java.time.Clock;
import java.time.ZonedDateTime;

public class WorkoutFullOutput {
    private WorkoutDocument document;

    public WorkoutFullOutput(WorkoutDocument document) {
        this.document = document;
    }

    public WorkoutFullViewModel toViewModel() {
        return new WorkoutFullViewModel(
                document.getWorkoutId().toLong(),
                formatCreationTime(document.getCreationDateTime()),
                document.getWorkoutTitle().toString(),
                new MuscleGroupsFullOutput(document.getMuscleGroups()).toViewModel()
        );
    }

    private static long formatCreationTime(ZonedDateTime creationDateTime) {
        return creationDateTime.withZoneSameInstant(Clock.systemDefaultZone().getZone()).toInstant().toEpochMilli();
    }

}
