package com.zihler.fitness_tracker.application.outbound_ports.presenters;

import com.zihler.fitness_tracker.application.outbound_ports.documents.DisplayableWorkouts;

public interface WorkoutsInOverviewPresenter {
    void present(DisplayableWorkouts workouts);
}
