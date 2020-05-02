package com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkouts;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsPresenter;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.context.ViewAllWorkoutsContext;
import com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.inbound_port.ViewAllWorkouts;

public class ViewAllWorkoutsUseCase implements ViewAllWorkouts {
    private FetchWorkouts fetchWorkouts;

    public ViewAllWorkoutsUseCase(FetchWorkouts fetchWorkouts) {
        this.fetchWorkouts = fetchWorkouts;
    }

    @Override
    public void invokeWith(WorkoutsPresenter output) {
        ViewAllWorkoutsContext.initialize(fetchWorkouts, output)
                .enactUseCase();
    }

}
