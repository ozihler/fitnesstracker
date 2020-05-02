package com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsPresenter;

public interface ViewAllWorkouts {
    void invokeWith(WorkoutsPresenter output);
}
