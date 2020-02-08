package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.outputs.FullSetsOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;

class ExerciseOutput {
    private ExerciseDocument exercise;

    ExerciseOutput(ExerciseDocument exercise) {
        this.exercise = exercise;
    }

    ExerciseViewModel toViewModel() {
        var setsOutput = new FullSetsOutput(exercise.getSets());

        return new ExerciseViewModel(
                exercise.getName().toString(),
                setsOutput.toViewModel());
    }


}
