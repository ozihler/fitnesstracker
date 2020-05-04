package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.domain.values.Exercises;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExercisesFilesOutput {
    private Exercises exercises;

    public ExercisesFilesOutput(Exercises exercises) {
        this.exercises = exercises;
    }

    public List<ExerciseViewModel> files() {
        return exercises.getExercises()
                .stream().map(e -> new ExerciseViewModel(
                        e.getName().toString(),
                        new SetsFilesOutput(e.getSets()).files(),
                        e.getMultiplier().value(),
                        e.isSelectable()))
                .collect(toList());
    }
}
