package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullWorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.viewmodels.FullWorkoutsViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FullWorkoutsOutput {
    private final FullWorkoutsViewModel workouts;

    public FullWorkoutsOutput(WorkoutsDocument workouts) {
        List<FullWorkoutViewModel> fullWorkouts = workouts.getWorkouts()
                .stream()
                .map(FullWorkoutOutput::new)
                .map(FullWorkoutOutput::toViewModel)
                .collect(toList());

        this.workouts = new FullWorkoutsViewModel(fullWorkouts);
    }


    public FullWorkoutsViewModel workouts() {
        return workouts;
    }
}
