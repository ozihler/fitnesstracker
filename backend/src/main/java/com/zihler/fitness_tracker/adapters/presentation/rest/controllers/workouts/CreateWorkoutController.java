package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestWorkoutPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutAndMuscleGroupNamesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.GenerateWorkoutId;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.use_cases.workouts.create_workout.CreateWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.workouts.create_workout.inbound_port.CreateWorkout;
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

    public ResponseEntity<WorkoutAndMuscleGroupNamesViewModel> createWorkout() {
        var output = new RestWorkoutPresenter();

        createWorkout.invokeWith(output);

        return output.getResponse();
    }
}
