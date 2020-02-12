package com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.context;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkouts;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsInOverviewPresenter;
import com.zihler.fitness_tracker.application.use_cases.UseCaseContext;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.roles.DisplayableWorkouts;

public class ViewAllWorkoutsContext implements UseCaseContext {
    private final FetchWorkouts fetchWorkouts;
    private final WorkoutsInOverviewPresenter output;

    public ViewAllWorkoutsContext(FetchWorkouts fetchWorkouts, WorkoutsInOverviewPresenter output) {
        this.fetchWorkouts = fetchWorkouts;
        this.output = output;
    }

    public static ViewAllWorkoutsContext initialize(FetchWorkouts fetchWorkouts, WorkoutsInOverviewPresenter output) {
        return new ViewAllWorkoutsContext(fetchWorkouts, output);
    }

    @Override
    public void enactUseCase() {
        var displayableWorkouts = new DisplayableWorkouts(fetchWorkouts, output);
        displayableWorkouts.present();
    }
}
