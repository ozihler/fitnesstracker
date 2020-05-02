package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullExerciseViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.outputs.FullSetsOutput;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;

class FullExerciseOutput {
    private ExerciseDocument exercise;

    FullExerciseOutput(ExerciseDocument exercise) {
        this.exercise = exercise;
    }

    public FullExerciseViewModel toViewModel() {
        return new FullExerciseViewModel(
                exercise.getName().toString(),
                new FullSetsOutput(exercise.getSets()).toViewModel());
    }
}
