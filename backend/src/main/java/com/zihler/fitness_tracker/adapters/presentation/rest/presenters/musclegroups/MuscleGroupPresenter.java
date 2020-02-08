package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;

public interface MuscleGroupPresenter {
    void present(MuscleGroupDocument document);
}
