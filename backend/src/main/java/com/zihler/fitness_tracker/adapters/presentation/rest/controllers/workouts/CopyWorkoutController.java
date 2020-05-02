package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs.WorkoutIdInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.WorkoutIdPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.GenerateWorkoutId;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.CopyWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.inbound_port.CopyWorkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CopyWorkoutController {
    private CopyWorkout copyWorkout;

    @Autowired
    public CopyWorkoutController(FetchWorkout fetchWorkout, StoreWorkout storeWorkout, GenerateWorkoutId generateWorkoutId) {
        this.copyWorkout = new CopyWorkoutUseCase(fetchWorkout, storeWorkout, generateWorkoutId);
    }

    public ResponseEntity<WorkoutViewModel> copyWorkout(WorkoutViewModel request) {
        var input = new WorkoutIdInput(request);
        var output = new WorkoutIdPresenter();

        this.copyWorkout.withId(input.workoutId(), output);

        return output.getResponse();
    }
}
