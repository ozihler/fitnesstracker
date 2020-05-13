package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class WorkoutsInOverviewOutput {
    private WorkoutsDocument workouts;

    public WorkoutsInOverviewOutput(WorkoutsDocument workouts) {
        this.workouts = workouts;
    }

    public WorkoutsViewModel workouts() {
        List<WorkoutViewModel> viewModels = workouts.getWorkouts()
                .stream()
                .filter(workout -> !workout.isDeleted())
                .map(WorkoutInOverviewOutput::new)
                .map(WorkoutInOverviewOutput::toViewModel)
                .sorted((a, b) -> -Long.compare(a.getCreationDate(), b.getCreationDate()))
                .collect(toList());

        return new WorkoutsViewModel(viewModels);
    }
}
