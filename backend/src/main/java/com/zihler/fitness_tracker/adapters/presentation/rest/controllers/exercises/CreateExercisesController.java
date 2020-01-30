package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs.CreateExercisesInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseNames;
import com.zihler.fitness_tracker.application.outbound_ports.StoreExercises;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.application.use_cases.create_exercises.inbound_port.CreateExercises;
import com.zihler.fitness_tracker.application.use_cases.create_exercises.CreateExercisesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateExercisesController {

    private CreateExercises createExercises;

    @Autowired
    public CreateExercisesController(FetchMuscleGroup fetchMuscleGroup, StoreExercises storeExercises) {
        this.createExercises = new CreateExercisesUseCase(fetchMuscleGroup, storeExercises);
    }

    public void forMuscleGroup(String muscleGroupName,
                               ExerciseNames exercisesNames,
                               ExercisesPresenter output) {

        var input = new CreateExercisesInput(muscleGroupName, exercisesNames.getInput());

        this.createExercises.forMuscleGroup(input.muscleGroup(), input.exercises(), output);
    }

}
