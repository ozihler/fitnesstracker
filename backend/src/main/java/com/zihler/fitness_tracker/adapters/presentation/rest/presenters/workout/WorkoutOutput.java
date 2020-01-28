package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WorkoutOutput {
    private WorkoutDocument workoutDocument;

    public WorkoutOutput(WorkoutDocument workoutDocument) {
        this.workoutDocument = workoutDocument;
    }

    public WorkoutViewModel toViewModel() {
        return new WorkoutViewModel(
                workoutDocument.getGid().toLong(),
                workoutDocument.getCreationDateTime().toInstant().toEpochMilli(),
                workoutDocument.getWorkoutTitle().toString(),
                toViewModel(workoutDocument.getMuscleGroups())
        );
    }

    private Set<MuscleGroupViewModel> toViewModel(MuscleGroupsDocument muscleGroups) {
        return muscleGroups.getMuscleGroups().stream().map(m->new MuscleGroupViewModel(m.getName().toString())).collect(toSet());
    }
}
