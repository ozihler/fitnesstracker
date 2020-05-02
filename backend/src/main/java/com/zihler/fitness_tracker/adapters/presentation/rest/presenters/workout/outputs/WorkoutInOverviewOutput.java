package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;

public class WorkoutInOverviewOutput {
    private WorkoutDocument workout;

    public WorkoutInOverviewOutput(WorkoutDocument workout) {
        this.workout = workout;
    }

    public WorkoutViewModel toViewModel() {
        return new WorkoutViewModel(
                workout.getWorkoutId().toString(),
                workout.getCreationDate().toMillis(),
                workout.getWorkoutTitle().toString());
    }

}
