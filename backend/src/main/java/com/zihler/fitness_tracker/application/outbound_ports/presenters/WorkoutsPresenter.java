package com.zihler.fitness_tracker.application.outbound_ports.presenters;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;

public interface WorkoutsPresenter {
    void present(WorkoutsDocument workouts);
}
