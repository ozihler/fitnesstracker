package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs;

import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Names;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class CreateExercisesInput {
    private String muscleGroupName;
    private String exercisesNames;

    public CreateExercisesInput(String muscleGroupName, String exercisesNames) {
        this.muscleGroupName = muscleGroupName;
        this.exercisesNames = exercisesNames;
    }

    public MuscleGroupDocument muscleGroup() {
        return MuscleGroupDocument.of(Name.of(this.muscleGroupName));
    }

    public ExercisesDocument exercises() {
        Set<ExerciseDocument> exercises = Names.in(exercisesNames)
                .values()
                .stream()
                .map(ExerciseDocument::of)
                .collect(toSet());

        return ExercisesDocument.of(exercises);
    }
}
