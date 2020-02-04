package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs.WorkoutToCreateInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToCreateRequest;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.create_workout.CreateWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.create_workout.inbound_port.CreateWorkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateWorkoutController {

    private CreateWorkout createWorkout;

    @Autowired
    public CreateWorkoutController(StoreWorkout workouts) {
        this.createWorkout = new CreateWorkoutUseCase(workouts);
    }

    public void createWorkout(WorkoutToCreateRequest request, WorkoutPresenter presenter) {
        var input = new WorkoutToCreateInput(request);
        this.createWorkout.with(input.workoutTitle(), presenter);
    }
}
