package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs.MuscleGroupOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.outputs.FullSetsOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;

class ExerciseOutput {
    private ExerciseDocument exercise;

    ExerciseOutput(ExerciseDocument exercise) {
        this.exercise = exercise;
    }

    ExerciseViewModel toViewModel() {
        var muscleGroupOutput = new MuscleGroupOutput(exercise.getMuscleGroup());
        var setsOuput = new FullSetsOutput(exercise.getSets());

        return new ExerciseViewModel(
                muscleGroupOutput.toViewModel(),
                exercise.getName().toString(),
                setsOuput.toViewModel());
    }


}