package com.zihler.fitness_tracker.application.outbound_ports.presenters;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;

public interface MuscleGroupsPresenter {
    void present(MuscleGroupsDocument workoutDocument);
}
