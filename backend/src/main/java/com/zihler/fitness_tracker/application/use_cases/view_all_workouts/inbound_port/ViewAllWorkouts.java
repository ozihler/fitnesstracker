package com.zihler.fitness_tracker.application.use_cases.view_all_workouts.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsInOverviewPresenter;

public interface ViewAllWorkouts {
    void invokeWith(WorkoutsInOverviewPresenter output);
}
