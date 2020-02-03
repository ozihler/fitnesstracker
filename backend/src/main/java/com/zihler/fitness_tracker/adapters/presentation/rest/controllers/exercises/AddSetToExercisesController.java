package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs.AddSetToExerciseInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetDetails;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreSet;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.SetPresenter;
import com.zihler.fitness_tracker.application.use_cases.add_set_to_exercise.AddSetToExerciseUseCase;
import com.zihler.fitness_tracker.application.use_cases.add_set_to_exercise.inbound_port.AddSetToExercise;
import org.springframework.stereotype.Component;

@Component
public class AddSetToExercisesController {

    private AddSetToExercise addSetToExercise;

    public AddSetToExercisesController(StoreSet storeSet) {
        this.addSetToExercise = new AddSetToExerciseUseCase(storeSet);
    }

    public void addSetToExercises(String exerciseName, SetDetails setDetails, SetPresenter output) {
        var input = new AddSetToExerciseInput(exerciseName, setDetails);

        addSetToExercise.invokeWith(input.exerciseName(), input.set(), output);
    }

}
