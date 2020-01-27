package com.zihler.fitness_tracker.adapters.presentation.controllers;

import com.zihler.fitness_tracker.adapters.presentation.rest.json.WorkoutToCreateRequest;
import com.zihler.fitness_tracker.application.outbound_ports.IStoreWorkouts;
import com.zihler.fitness_tracker.application.outbound_ports.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.create_workout.CreateWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.create_workout.inbound_port.CreateWorkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateWorkoutController {

    private CreateWorkout createWorkout;

    @Autowired
    public CreateWorkoutController(IStoreWorkouts workouts) {
        this.createWorkout = new CreateWorkoutUseCase(workouts);
    }

    public void createWorkout(WorkoutToCreateRequest request, WorkoutPresenter presenter) {
        var input = new WorkoutToCreateInput(request);
        this.createWorkout.with(input.workoutTitle(), presenter);
    }
}
