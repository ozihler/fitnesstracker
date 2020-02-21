package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutInOverviewViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsInOverviewViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class WorkoutsInOverviewOutput {
    private WorkoutsDocument workouts;

    public WorkoutsInOverviewOutput(WorkoutsDocument workouts) {
        this.workouts = workouts;
    }

    private static WorkoutInOverviewViewModel toViewModel(WorkoutDocument w) {
        return new WorkoutInOverviewViewModel(w.getWorkoutId().toLong(),
                w.getCreationDate().toMillis(),
                w.getWorkoutTitle().toString());
    }

    public WorkoutsInOverviewViewModel workouts() {
        List<WorkoutInOverviewViewModel> viewModels = workouts.getWorkouts()
                .stream()
                .map(WorkoutsInOverviewOutput::toViewModel)
                .sorted((a, b) -> -Long.compare(a.getCreationDate(), b.getCreationDate()))
                .collect(toList());

        return new WorkoutsInOverviewViewModel(viewModels);
    }
}
