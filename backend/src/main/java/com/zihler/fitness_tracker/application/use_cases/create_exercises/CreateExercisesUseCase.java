package com.zihler.fitness_tracker.application.use_cases.create_exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs.ExercisesToCreate;
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
    public void forMuscleGroup(ExercisesToCreate exercisesToCreate,
                               ExercisesPresenter output) {

        // todo create roles
        MuscleGroup muscleGroup = this.fetchMuscleGroup.byName(exercisesToCreate.parentMuscleGroup());

        Exercises exercisesToStore = toEntities(exercisesToCreate, muscleGroup);

        Exercises storedExercises = this.storeExercises.in(muscleGroup.getName(), exercisesToStore);

        ExercisesDocument exercisesDocumentToPresent = toDocuments(storedExercises);

        output.present(exercisesDocumentToPresent);

    }

    private Exercises toEntities(ExercisesToCreate exercisesToCreate, MuscleGroup muscleGroup) {
        return Exercises.of(exercisesToCreate.exercisesToCreate()
                .values()
                .stream()
                .map(name -> new Exercise(muscleGroup, name))
                .collect(toSet()));
    }

    private ExercisesDocument toDocuments(Exercises storedExercises) {
        return ExercisesDocument.of(storedExercises.getExercises()
                .stream()
                .map(e -> ExerciseDocument.of(MuscleGroupDocument.of(e.getMuscleGroup()), e.getName()))
                .collect(toSet()));
    }


}