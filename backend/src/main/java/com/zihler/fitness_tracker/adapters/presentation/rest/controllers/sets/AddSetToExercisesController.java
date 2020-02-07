package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.inputs.AddSetToExerciseInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.requests.SetDetails;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.RestSetPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullSetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreSet;
import com.zihler.fitness_tracker.application.use_cases.add_set_to_exercise.AddSetToExerciseUseCase;
import com.zihler.fitness_tracker.application.use_cases.add_set_to_exercise.inbound_port.AddSetToExercise;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddSetToExercisesController {

    private AddSetToExercise addSetToExercise;

    public AddSetToExercisesController(StoreSet storeSet) {
        addSetToExercise = new AddSetToExerciseUseCase(storeSet);
    }

    public ResponseEntity<FullSetViewModel> addSetToExercises(String exerciseName, SetDetails setDetails) {
        var input = new AddSetToExerciseInput(exerciseName, setDetails);
        var output = new RestSetPresenter();

        addSetToExercise.invokeWith(input.exerciseName(), input.set(), output);

        return output.getResponse();
    }
}
