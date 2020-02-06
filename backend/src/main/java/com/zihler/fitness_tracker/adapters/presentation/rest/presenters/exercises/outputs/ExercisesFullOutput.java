package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.ExerciseFullViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExercisesFullOutput {
    private ExercisesDocument exercises;

    public ExercisesFullOutput(ExercisesDocument exercises) {
        this.exercises = exercises;
    }

    public List<ExerciseFullViewModel> toViewModel() {
        return exercises.getExercises()
                .stream()
                .map(ExerciseFullOutput::new)
                .map(ExerciseFullOutput::toViewModel)
                .collect(toList());
    }

}
