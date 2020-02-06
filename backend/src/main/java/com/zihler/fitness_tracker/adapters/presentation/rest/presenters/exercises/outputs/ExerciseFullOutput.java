package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.ExerciseFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.outputs.SetsOutput;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;

class ExerciseFullOutput {
    private ExerciseDocument exercise;

    ExerciseFullOutput(ExerciseDocument exercise) {
        this.exercise = exercise;
    }

    public ExerciseFullViewModel toViewModel() {
        return new ExerciseFullViewModel(
                exercise.getName().toString(),
                new SetsOutput(exercise.getSets()).toViewModel());
    }
}
