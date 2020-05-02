package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs.FullMuscleGroupsOutput;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;

public class FullWorkoutOutput {
    private WorkoutDocument document;

    public FullWorkoutOutput(WorkoutDocument document) {
        this.document = document;
    }

    public WorkoutViewModel toViewModel() {
        return new WorkoutViewModel(
                document.getWorkoutId().toString(),
                document.getCreationDate().toMillis(),
                document.getWorkoutTitle().toString(),
                new FullMuscleGroupsOutput(document.getMuscleGroups()).toViewModel()
        );
    }

}
