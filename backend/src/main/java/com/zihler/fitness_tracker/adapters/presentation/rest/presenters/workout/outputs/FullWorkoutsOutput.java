package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FullWorkoutsOutput {
    private final WorkoutsViewModel workouts;

    public FullWorkoutsOutput(WorkoutsDocument workouts) {
        List<WorkoutViewModel> fullWorkouts = workouts.getWorkouts()
                .stream()
                .map(FullWorkoutOutput::new)
                .map(FullWorkoutOutput::toViewModel)
                .collect(toList());

        this.workouts = new WorkoutsViewModel(fullWorkouts);
    }


    public WorkoutsViewModel workouts() {
        return workouts;
    }
}
