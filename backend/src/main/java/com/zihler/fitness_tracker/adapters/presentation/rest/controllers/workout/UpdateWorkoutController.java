package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.update_workouts.UpdateWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.update_workouts.inbound_port.UpdateWorkout;
import org.springframework.stereotype.Component;

@Component
public class UpdateWorkoutController {
    private UpdateWorkout updateWorkout;

    public UpdateWorkoutController(FetchWorkout fetchWorkout, StoreWorkout storeWorkouts) {
        this.updateWorkout = new UpdateWorkoutUseCase(fetchWorkout,storeWorkouts, null);
    }

    public void updateWorkout(WorkoutViewModel request, WorkoutPresenter output) {
        var input = new WorkoutToUpdateInput(request);

        this.updateWorkout.callWith(input.workout(), output);
    }
}
