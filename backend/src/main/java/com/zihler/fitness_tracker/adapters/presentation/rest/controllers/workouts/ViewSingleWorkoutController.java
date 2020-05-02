package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs.WorkoutToRetrieveInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullWorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestFullWorkoutPresenter;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_single_workout.ViewSingleWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_single_workout.inbound_port.ViewSingleWorkout;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ViewSingleWorkoutController {
    private ViewSingleWorkout viewSingleWorkout;

    public ViewSingleWorkoutController(FetchWorkout fetchWorkout) {
        viewSingleWorkout = new ViewSingleWorkoutUseCase(fetchWorkout);
    }

    public ResponseEntity<FullWorkoutViewModel> viewWorkoutWithId(String workoutGid) {
        var input = new WorkoutToRetrieveInput(workoutGid);
        var output = new RestFullWorkoutPresenter();

        viewSingleWorkout.invokeWith(input.workoutGid(), output);

        return output.getResponse();
    }
}
