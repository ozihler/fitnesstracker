package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs.CreateExercisesInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests.ExerciseNames;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.RestExercisesPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreExercises;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.use_cases.create_exercises.CreateExercisesUseCase;
import com.zihler.fitness_tracker.application.use_cases.create_exercises.inbound_port.CreateExercises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CreateExercisesController {

    private CreateExercises createExercises;

    @Autowired
    public CreateExercisesController(FetchMuscleGroup fetchMuscleGroup, StoreExercises storeExercises) {
        this.createExercises = new CreateExercisesUseCase(fetchMuscleGroup, storeExercises);
    }

    public ResponseEntity<ExercisesViewModel> forMuscleGroup(String muscleGroupName, ExerciseNames exercisesNames) {
        var input = new CreateExercisesInput(muscleGroupName, exercisesNames.getInput());
        var output = new RestExercisesPresenter();

        this.createExercises.forMuscleGroup(input.toCreate(), output);

        return output.getResponse();
    }
}
