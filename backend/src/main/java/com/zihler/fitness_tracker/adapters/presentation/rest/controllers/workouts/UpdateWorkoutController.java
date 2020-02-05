package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs.WorkoutToUpdateInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestUpdatedWorkoutPresenter;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercise;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.use_cases.update_workout.UpdateWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.update_workout.inbound_port.UpdateWorkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateWorkoutController {
    private UpdateWorkout updateWorkout;

    @Autowired
    public UpdateWorkoutController(FetchWorkout fetchWorkout, StoreWorkout storeWorkout, FetchMuscleGroup fetchMuscleGroup, FetchExercise fetchExercise) {
        updateWorkout = new UpdateWorkoutUseCase(fetchWorkout, storeWorkout, fetchMuscleGroup, fetchExercise);
    }

    public ResponseEntity<WorkoutFullViewModel> updateWorkout(WorkoutToUpdate request) {
        var input = new WorkoutToUpdateInput(request);
        var output = new RestUpdatedWorkoutPresenter();

        updateWorkout.invokeWith(input.workout(), output);

        return output.getResponse();
    }
}
