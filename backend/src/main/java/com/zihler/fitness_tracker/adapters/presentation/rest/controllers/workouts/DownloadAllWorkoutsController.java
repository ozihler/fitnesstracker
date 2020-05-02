package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestDownloadedWorkoutsPresenter;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkouts;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.ViewAllWorkoutsUseCase;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.inbound_port.ViewAllWorkouts;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DownloadAllWorkoutsController {
    private final ViewAllWorkouts viewAllWorkouts;

    public DownloadAllWorkoutsController(FetchWorkouts fetchWorkouts) {
        viewAllWorkouts = new ViewAllWorkoutsUseCase(fetchWorkouts);
    }

    public ResponseEntity<InputStreamResource> downloadAllWorkouts() {
        var output = new RestDownloadedWorkoutsPresenter();

        viewAllWorkouts.invokeWith(output);

        return output.getResponse();
    }
}
