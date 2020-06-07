package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs.SetFilesInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.Multiplier;
import com.zihler.fitness_tracker.domain.values.Name;

import java.util.List;
import java.util.stream.Collectors;

public class ExercisesFilesInput {
    private final List<ExerciseViewModel> exercises;

    public ExercisesFilesInput(List<ExerciseViewModel> exercises) {
        this.exercises = exercises;
    }

    Exercises exercises() {
        return Exercises.of(
                exercises.stream()
                        .map(this::toExercise)
                        .collect(Collectors.toList()));
    }

    private Exercise toExercise(ExerciseViewModel e) {
        return new Exercise(Name.of(e.getName()),
                new SetFilesInput(e.getSets()).sets(),
                Multiplier.of(e.getMultiplier()));
    }
}
