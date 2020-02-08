package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs.ExerciseNameInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.RestExerciseNamePresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseNameViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercise;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreExercise;
import com.zihler.fitness_tracker.application.use_cases.exercises.make_exercise_unselectable.MakeExerciseUnselectableUseCase;
import com.zihler.fitness_tracker.application.use_cases.exercises.make_exercise_unselectable.inbound_port.MakeExerciseUnselectable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MakeExerciseUnselectableController {
    private MakeExerciseUnselectable makeExerciseUnselectable;

    @Autowired
    public MakeExerciseUnselectableController(FetchExercise fetchExercise, StoreExercise storeExercise) {
        makeExerciseUnselectable = new MakeExerciseUnselectableUseCase(fetchExercise, storeExercise);
    }

    public ResponseEntity<ExerciseNameViewModel> makeUnselectable(String exerciseName) {
        var output = new RestExerciseNamePresenter();
        var input = new ExerciseNameInput(exerciseName);

        makeExerciseUnselectable.invokeWith(input, output);

        return output.getResponse();
    }
}
