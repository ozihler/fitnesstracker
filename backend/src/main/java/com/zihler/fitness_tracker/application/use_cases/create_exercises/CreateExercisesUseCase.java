package com.zihler.fitness_tracker.application.use_cases.create_exercises;

import com.zihler.fitness_tracker.application.outbound_ports.StoreExercises;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.application.use_cases.create_exercises.inbound_port.CreateExercises;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercises;

import static java.util.stream.Collectors.toSet;

public class CreateExercisesUseCase implements CreateExercises {

    private FetchMuscleGroup fetchMuscleGroup;
    private StoreExercises storeExercises;

    public CreateExercisesUseCase(FetchMuscleGroup fetchMuscleGroup, StoreExercises storeExercises) {
        this.fetchMuscleGroup = fetchMuscleGroup;
        this.storeExercises = storeExercises;
    }

    @Override
    public void forMuscleGroup(MuscleGroupDocument muscleGroupToExtend,
                               ExercisesDocument exercisesToCreate,
                               ExercisesPresenter output) {

        // todo create roles
        MuscleGroup muscleGroup = this.fetchMuscleGroup.byName(muscleGroupToExtend.getName());

        Exercises exercisesToStore = toEntities(exercisesToCreate);

        Exercises storedExercises = this.storeExercises.in(muscleGroup.getName(), exercisesToStore);

        ExercisesDocument exercisesDocumentToPresent = toDocuments(storedExercises);

        output.present(exercisesDocumentToPresent);

    }

    private ExercisesDocument toDocuments(Exercises storedExercises) {
        return ExercisesDocument.of(storedExercises.getExercises()
                .stream()
                .map(Exercise::getName)
                .map(ExerciseDocument::of)
                .collect(toSet()));
    }

    private Exercises toEntities(ExercisesDocument exercisesToCreate) {
        return Exercises.of(exercisesToCreate.getExercises()
                .stream()
                .map(ExerciseDocument::getName)
                .map(Exercise::new)
                .collect(toSet()));
    }


}
