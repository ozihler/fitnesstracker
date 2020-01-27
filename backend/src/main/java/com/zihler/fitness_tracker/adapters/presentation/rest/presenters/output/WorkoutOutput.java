package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.output;

import com.zihler.fitness_tracker.adapters.presentation.rest.json.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.WorkoutDocument;

public class WorkoutOutput {
    private WorkoutDocument workoutDocument;

    public WorkoutOutput(WorkoutDocument workoutDocument) {
        this.workoutDocument = workoutDocument;
    }

    public WorkoutViewModel toViewModel() {
        return new WorkoutViewModel(
                workoutDocument.getId().toLong(),
                workoutDocument.getCreationDateTime().toInstant().toEpochMilli(),
                workoutDocument.getWorkoutTitle().toString()
        );
    }
}
