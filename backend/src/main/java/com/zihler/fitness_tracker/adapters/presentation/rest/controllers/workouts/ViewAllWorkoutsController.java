package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestWorkoutsPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkouts;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.ViewAllWorkoutsUseCase;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.inbound_port.ViewAllWorkouts;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ViewAllWorkoutsController {
    private final ViewAllWorkouts viewAllWorkouts;

    public ViewAllWorkoutsController(FetchWorkouts fetchWorkouts) {
        viewAllWorkouts = new ViewAllWorkoutsUseCase(fetchWorkouts);
    }

    public ResponseEntity<WorkoutsViewModel> viewAllWorkouts() {
        var output = new RestWorkoutsPresenter();

        viewAllWorkouts.invokeWith(output);

        return output.getResponse();
    }
}
