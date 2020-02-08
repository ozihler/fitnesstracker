package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs.MuscleGroupNameInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.RestExercisesPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercises;
import com.zihler.fitness_tracker.application.use_cases.exercises.view_all_exercises_for_muscle_group.ViewAllExercisesForMuscleGroupUseCase;
import com.zihler.fitness_tracker.application.use_cases.exercises.view_all_exercises_for_muscle_group.inbound_port.ViewAllExercisesForMuscleGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ViewAllExercisesController {
    private ViewAllExercisesForMuscleGroup viewAllExercisesForMuscleGroup;

    @Autowired
    public ViewAllExercisesController(FetchExercises fetchExercises) {
        viewAllExercisesForMuscleGroup = new ViewAllExercisesForMuscleGroupUseCase(fetchExercises);
    }

    public ResponseEntity<ExercisesViewModel> viewAllExercisesOfMuscleGroup(String muscleGroupName) {
        var input = new MuscleGroupNameInput(muscleGroupName);
        var output = new RestExercisesPresenter();

        viewAllExercisesForMuscleGroup.invokeWith(input.muscleGroupName(), output);

        return output.getResponse();
    }

}
