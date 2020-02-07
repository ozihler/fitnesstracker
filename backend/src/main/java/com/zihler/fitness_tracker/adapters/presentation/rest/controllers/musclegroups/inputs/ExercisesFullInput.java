package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.inputs.SetsFullInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullExerciseViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;
import com.zihler.fitness_tracker.domain.values.Name;

import java.util.List;

import static java.util.stream.Collectors.toSet;

class ExercisesFullInput {
    private List<FullExerciseViewModel> exercises;

    ExercisesFullInput(List<FullExerciseViewModel> exercises) {
        this.exercises = exercises;
    }

    ExercisesDocument toDocument() {
        return ExercisesDocument.of(
                exercises.stream()
                        .map(this::toDocument)
                        .collect(toSet())
        );
    }

    private ExerciseDocument toDocument(FullExerciseViewModel e) {
        return ExerciseDocument.of(null,
                Name.of(e.getName()),
                new SetsFullInput(e.getSets()).toDocument());
    }
}
