package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.application.outbound_ports.documents.DisplayableWorkouts;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkouts;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsInOverviewPresenter;
import com.zihler.fitness_tracker.domain.values.Workouts;
import org.springframework.stereotype.Component;

@Component
public class ViewAllWorkoutsController {
    private FetchWorkouts fetchWorkouts;

    public ViewAllWorkoutsController(FetchWorkouts fetchWorkouts) {
        this.fetchWorkouts = fetchWorkouts;
    }

    public void viewAllWorkouts(WorkoutsInOverviewPresenter output) {
        Workouts allWorkouts = this.fetchWorkouts.all();
        output.present(toDocument(allWorkouts));
    }

    private DisplayableWorkouts toDocument(Workouts workouts) {
        return DisplayableWorkouts.of(workouts);
    }
}
