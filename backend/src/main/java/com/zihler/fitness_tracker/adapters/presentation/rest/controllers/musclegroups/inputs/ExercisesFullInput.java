package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.inputs.SetsFullInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;
import com.zihler.fitness_tracker.domain.values.Name;

import java.util.List;

import static java.util.stream.Collectors.toList;

class ExercisesFullInput {
    private List<ExerciseViewModel> exercises;

    ExercisesFullInput(List<ExerciseViewModel> exercises) {
        this.exercises = exercises;
    }

    ExercisesDocument toDocument() {
        return ExercisesDocument.of(
                exercises.stream()
                        .map(this::toDocument)
                        .collect(toList())
        );
    }

    private ExerciseDocument toDocument(ExerciseViewModel e) {
        return ExerciseDocument.of(Name.of(e.getName()), new SetsFullInput(e.getSets()).toDocument());
    }
}
