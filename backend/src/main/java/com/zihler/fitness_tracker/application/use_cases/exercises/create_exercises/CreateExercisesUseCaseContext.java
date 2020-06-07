package com.zihler.fitness_tracker.application.use_cases.exercises.create_exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests.ExercisesToCreate;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreExercises;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.application.use_cases.UseCaseContext;
import com.zihler.fitness_tracker.application.use_cases.exercises.create_exercises.roles.MuscleGroupToExtend;
import com.zihler.fitness_tracker.domain.entities.ExerciseFactory;
import com.zihler.fitness_tracker.domain.values.Exercises;

import static java.util.stream.Collectors.toList;

public class CreateExercisesUseCaseContext implements UseCaseContext {
    private MuscleGroupToExtend muscleGroupToExtend;
    private Exercises exercises;

    private CreateExercisesUseCaseContext(MuscleGroupToExtend muscleGroupToExtend, Exercises exercises) {
        this.muscleGroupToExtend = muscleGroupToExtend;
        this.exercises = exercises;
    }

    private CreateExercisesUseCaseContext() {

    }

    public static CreateExercisesUseCaseContext initialize(FetchMuscleGroup fetchMuscleGroup, StoreExercises storeExercises, ExercisesToCreate exercisesToCreate, ExercisesPresenter output) {

        var muscleGroup = fetchMuscleGroup.by(exercisesToCreate.parentMuscleGroup());
        var muscleGroupToExtend = new MuscleGroupToExtend(muscleGroup, storeExercises, output);
        var exercisesToStore = instantiate(exercisesToCreate);


        return new CreateExercisesUseCaseContext(muscleGroupToExtend, exercisesToStore);
    }

    private static Exercises instantiate(ExercisesToCreate exercisesToCreate) {
        return Exercises.of(exercisesToCreate.exercisesToCreate()
                .values()
                .stream()
                .map(name -> ExerciseFactory.createExerciseFrom(name.toString()))
                .collect(toList()));
    }

    @Override
    public void enactUseCase() {
        muscleGroupToExtend.addAndStore(exercises);
        muscleGroupToExtend.present();
    }


}
