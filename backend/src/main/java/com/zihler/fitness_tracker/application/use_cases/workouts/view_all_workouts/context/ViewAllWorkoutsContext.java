package com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.context;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkouts;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsPresenter;
import com.zihler.fitness_tracker.application.use_cases.UseCaseContext;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.roles.DisplayableWorkouts;

public class ViewAllWorkoutsContext implements UseCaseContext {
    private final FetchWorkouts fetchWorkouts;
    private final WorkoutsPresenter output;

    public ViewAllWorkoutsContext(FetchWorkouts fetchWorkouts, WorkoutsPresenter output) {
        this.fetchWorkouts = fetchWorkouts;
        this.output = output;
    }

    public static ViewAllWorkoutsContext initialize(FetchWorkouts fetchWorkouts, WorkoutsPresenter output) {
        return new ViewAllWorkoutsContext(fetchWorkouts, output);
    }

    @Override
    public void enactUseCase() {
        new DisplayableWorkouts(fetchWorkouts, output).present();
    }
}
