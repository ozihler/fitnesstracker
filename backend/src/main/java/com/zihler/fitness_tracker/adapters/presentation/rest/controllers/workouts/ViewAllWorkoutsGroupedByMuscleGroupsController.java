package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestWorkoutsInOverviewGroupedByMuscleGroupPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.viewmodels.WorkoutsInOverviewGroupedByMuscleGroupViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkouts;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.ViewAllWorkoutsUseCase;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.inbound_port.ViewAllWorkouts;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ViewAllWorkoutsGroupedByMuscleGroupsController {
    private final ViewAllWorkouts viewAllWorkouts;

    public ViewAllWorkoutsGroupedByMuscleGroupsController(FetchWorkouts fetchWorkouts) {
        viewAllWorkouts = new ViewAllWorkoutsUseCase(fetchWorkouts);
    }

    public ResponseEntity<WorkoutsInOverviewGroupedByMuscleGroupViewModel> viewAllWorkoutsGroupedByMuscleGroups() {
        var output = new RestWorkoutsInOverviewGroupedByMuscleGroupPresenter();

        viewAllWorkouts.invokeWith(output);

        return output.getResponse();
    }
}
