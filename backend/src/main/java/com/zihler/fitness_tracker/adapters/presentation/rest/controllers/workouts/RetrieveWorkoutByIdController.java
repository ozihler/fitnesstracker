package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs.WorkoutToRetrieveInput;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.retrieve_workout_by_id.RetrieveWorkoutByIdUseCase;
import com.zihler.fitness_tracker.application.use_cases.retrieve_workout_by_id.inbound_port.RetrieveWorkoutById;
import org.springframework.stereotype.Component;

@Component
public class RetrieveWorkoutByIdController {
    private RetrieveWorkoutById retrieveWorkoutById;

    public RetrieveWorkoutByIdController(FetchWorkout fetchWorkout) {
        this.retrieveWorkoutById = new RetrieveWorkoutByIdUseCase(fetchWorkout);
    }
    public void retrieveForId(String workoutGid, WorkoutPresenter output) {
        var input = new WorkoutToRetrieveInput(workoutGid);
        this.retrieveWorkoutById.invokeWith(input.workoutGid(), output);
    }
}
