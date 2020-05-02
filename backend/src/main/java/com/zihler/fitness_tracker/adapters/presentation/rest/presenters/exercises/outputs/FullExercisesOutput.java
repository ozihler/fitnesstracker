package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FullExercisesOutput {
    private ExercisesDocument exercises;

    public FullExercisesOutput(ExercisesDocument exercises) {
        this.exercises = exercises;
    }

    public List<ExerciseViewModel> toViewModel() {
        return exercises.getExercises()
                .stream()
                .map(FullExerciseOutput::new)
                .map(FullExerciseOutput::toViewModel)
                .collect(toList());
    }

}
