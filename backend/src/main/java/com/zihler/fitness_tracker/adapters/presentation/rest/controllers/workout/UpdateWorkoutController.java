package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout.inputs.WorkoutToUpdateInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.update_workout.UpdateWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.update_workout.inbound_port.UpdateWorkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateWorkoutController {
    private UpdateWorkout updateWorkout;

    @Autowired
    public UpdateWorkoutController(FetchWorkout fetchWorkout, StoreWorkout storeWorkout, FetchMuscleGroup fetchMuscleGroup) {
        this.updateWorkout = new UpdateWorkoutUseCase(fetchWorkout, storeWorkout, fetchMuscleGroup);
    }

    public void updateWorkout(WorkoutViewModel request, WorkoutPresenter output) {
        var input = new WorkoutToUpdateInput(request);

        this.updateWorkout.callWith(input.workout(), output);
    }
}
