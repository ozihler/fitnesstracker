package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.MuscleGroupFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs.ExercisesFullOutput;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;

class MuscleGroupFullOutput {
    private MuscleGroupDocument document;

    MuscleGroupFullOutput(MuscleGroupDocument document) {
        this.document = document;
    }


    public MuscleGroupFullViewModel toViewModel() {
        return new MuscleGroupFullViewModel(
                document.getName().toString(),
                new ExercisesFullOutput(document.getExercises()).toViewModel());


    }
}
