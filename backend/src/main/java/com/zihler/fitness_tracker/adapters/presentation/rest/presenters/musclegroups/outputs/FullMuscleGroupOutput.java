package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs.FullExercisesOutput;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;

class FullMuscleGroupOutput {
    private MuscleGroupDocument document;

    FullMuscleGroupOutput(MuscleGroupDocument document) {
        this.document = document;
    }


    public MuscleGroupViewModel toViewModel() {
        return new MuscleGroupViewModel(
                document.getName().toString(),
                new FullExercisesOutput(document.getExercises()).toViewModel(),
                document.isSelectable());


    }
}
