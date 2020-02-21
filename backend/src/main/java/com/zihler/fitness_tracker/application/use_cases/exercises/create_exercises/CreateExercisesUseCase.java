package com.zihler.fitness_tracker.application.use_cases.exercises.create_exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests.ExercisesToCreate;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreExercises;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.application.use_cases.exercises.create_exercises.inbound_port.CreateExercises;
import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;

import static java.util.stream.Collectors.toList;

public class CreateExercisesUseCase implements CreateExercises {

    private FetchMuscleGroup fetchMuscleGroup;
    private StoreExercises storeExercises;

    public CreateExercisesUseCase(FetchMuscleGroup fetchMuscleGroup, StoreExercises storeExercises) {
        this.fetchMuscleGroup = fetchMuscleGroup;
        this.storeExercises = storeExercises;
    }

    @Override
    public void forMuscleGroup(ExercisesToCreate exercisesToCreate,
                               ExercisesPresenter output) {

        // todo create roles
        MuscleGroup muscleGroup = fetchMuscleGroup.by(exercisesToCreate.parentMuscleGroup());

        Exercises exercisesToStore = toEntities(exercisesToCreate);

        Exercises storedExercises = storeExercises.forMuscleGroup(muscleGroup.getName(), exercisesToStore);

        ExercisesDocument exercisesDocumentToPresent = toDocuments(storedExercises);

        output.present(exercisesDocumentToPresent);

    }

    private Exercises toEntities(ExercisesToCreate exercisesToCreate) {
        return Exercises.of(exercisesToCreate.exercisesToCreate()
                .values()
                .stream()
                .map(Exercise::new)
                .collect(toList()));
    }

    private ExercisesDocument toDocuments(Exercises storedExercises) {
        return ExercisesDocument.of(storedExercises.getExercises()
                .stream()
                .map(ExerciseDocument::of)
                .collect(toList()));
    }


}
