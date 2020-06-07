package com.zihler.fitness_tracker.application.use_cases.exercises.create_exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests.ExercisesToCreate;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreExercises;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.application.use_cases.exercises.create_exercises.inbound_port.CreateExercises;

public class CreateExercisesUseCase implements CreateExercises {

    private final FetchMuscleGroup fetchMuscleGroup;
    private final StoreExercises storeExercises;

    public CreateExercisesUseCase(FetchMuscleGroup fetchMuscleGroup, StoreExercises storeExercises) {
        this.fetchMuscleGroup = fetchMuscleGroup;
        this.storeExercises = storeExercises;
    }

    @Override
    public void forMuscleGroup(ExercisesToCreate exercisesToCreate,
                               ExercisesPresenter output) {

        CreateExercisesUseCaseContext.initialize(fetchMuscleGroup, storeExercises, exercisesToCreate, output).
                enactUseCase();

    }


}
