package com.zihler.fitness_tracker.application.outbound_ports.presenters;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;

public interface WorkoutPresenter {
    void present(WorkoutDocument workoutDocument);
}
