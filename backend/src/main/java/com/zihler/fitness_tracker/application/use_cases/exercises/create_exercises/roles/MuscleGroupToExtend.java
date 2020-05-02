package com.zihler.fitness_tracker.application.use_cases.exercises.create_exercises.roles;

import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreExercises;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupToExtend {
    private MuscleGroup self;
    private StoreExercises storeExercises;
    private ExercisesPresenter output;
    private Exercises appendedExercises;

    public MuscleGroupToExtend(MuscleGroup self, StoreExercises storeExercises, ExercisesPresenter output) {
        this.self = self;
        this.storeExercises = storeExercises;
        this.output = output;
    }

    public void addAndStore(Exercises exercises) {
        appendedExercises = storeExercises.forMuscleGroup(self.getName(), exercises);
    }

    public void present() {
        ExercisesDocument exercisesDocumentToPresent = toDocuments(appendedExercises.getExercises());

        output.present(exercisesDocumentToPresent);
    }

    private ExercisesDocument toDocuments(List<Exercise> exercises) {
        return ExercisesDocument.of(exercises
                .stream()
                .map(ExerciseDocument::of)
                .collect(toList()));
    }
}
