package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExercisesOutput {
    private ExercisesDocument exercises;

    public ExercisesOutput(ExercisesDocument exercises) {
        this.exercises = exercises;
    }

    public ExercisesViewModel toViewModel() {
        return new ExercisesViewModel(toViewModels());
    }

    private List<ExerciseViewModel> toViewModels() {
        return exercises.getExercises()
                .stream()
                .map(ExerciseOutput::new)
                .map(ExerciseOutput::toViewModel)
                .collect(toList());
    }
}
