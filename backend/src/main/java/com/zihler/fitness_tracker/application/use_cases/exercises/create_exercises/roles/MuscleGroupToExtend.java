package com.zihler.fitness_tracker.application.use_cases.exercises.create_exercises.roles;

import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreExercises;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercises;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupToExtend {
    private final MuscleGroup self;
    private final StoreExercises storeExercises;
    private final ExercisesPresenter output;
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
                .map(e -> new ExerciseDocument(
                        e.getName(),
                        SetsDocument.of(e.getSets().getSets().stream().map(s -> SetDocument.of(s.getWeight(), s.getRepetitions(), s.getWaitingTime())).collect(toList())),
                        e.getMultiplier(),
                        e.isSelectable()))
                .collect(toList()));
    }
}
