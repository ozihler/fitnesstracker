package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs.WorkoutToCreateInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToCreate;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestWorkoutPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutAndMuscleGroupNamesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.GenerateWorkoutId;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.use_cases.create_workout.CreateWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.create_workout.inbound_port.CreateWorkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CreateWorkoutController {
    private CreateWorkout createWorkout;

    @Autowired
    public CreateWorkoutController(StoreWorkout workouts, GenerateWorkoutId generateWorkoutId) {
        createWorkout = new CreateWorkoutUseCase(workouts, generateWorkoutId);
    }

    public ResponseEntity<WorkoutAndMuscleGroupNamesViewModel> createWorkout(WorkoutToCreate request) {
        var input = new WorkoutToCreateInput(request);
        var output = new RestWorkoutPresenter();

        createWorkout.invokeWith(input.workoutTitle(), output);

        return output.getResponse();
    }
}
