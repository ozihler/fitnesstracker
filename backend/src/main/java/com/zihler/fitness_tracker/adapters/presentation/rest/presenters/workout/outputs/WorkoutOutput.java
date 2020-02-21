package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupNameViewModel;
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
                workoutDocument.getWorkoutId().toLong(),
                workoutDocument.getCreationDate().toMillis(),
                workoutDocument.getWorkoutTitle().toString(),
                toViewModel(workoutDocument.getMuscleGroups())
        );
    }

    private List<MuscleGroupNameViewModel> toViewModel(MuscleGroupsDocument muscleGroups) {
        return muscleGroups.getMuscleGroups().
                stream()
                .map(m -> new MuscleGroupNameViewModel(m.getName().toString()))
                .collect(toList());
    }
}
