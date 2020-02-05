package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutFullViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;

public class WorkoutFullOutput {
    private WorkoutDocument workoutDocument;

    public WorkoutFullOutput(WorkoutDocument workoutDocument) {
        this.workoutDocument = workoutDocument;
    }

    public WorkoutFullViewModel toViewModel() {
        return new WorkoutFullViewModel();
    }
}
