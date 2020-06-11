package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutAndMuscleGroupNamesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class WorkoutOutput {
    private WorkoutDocument workoutDocument;

    public WorkoutOutput(WorkoutDocument workoutDocument) {
        this.workoutDocument = workoutDocument;
    }

    public WorkoutAndMuscleGroupNamesViewModel toViewModel() {
        return new WorkoutAndMuscleGroupNamesViewModel(
                workoutDocument.getWorkoutId().toString(),
                workoutDocument.getCreationDate().toMillis(),
                toViewModel(workoutDocument.getMuscleGroups())
        );
    }

    private List<MuscleGroupViewModel> toViewModel(MuscleGroupsDocument muscleGroups) {
        return muscleGroups.getMuscleGroups().
                stream()
                .map(m -> new MuscleGroupViewModel(m.getName().toString()))
                .collect(toList());
    }
}
