package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ExercisesOutput {
    private ExercisesDocument exercises;

    public ExercisesOutput(ExercisesDocument exercises) {
        this.exercises = exercises;
    }

    public ExercisesViewModel toViewModel() {
        return new ExercisesViewModel(toViewModels());
    }

    private Set<ExerciseViewModel> toViewModels() {
        return exercises.getExercises()
                .stream()
                .map(ExerciseOutput::new)
                .map(ExerciseOutput::toViewModel)
                .collect(toSet());
    }
}
