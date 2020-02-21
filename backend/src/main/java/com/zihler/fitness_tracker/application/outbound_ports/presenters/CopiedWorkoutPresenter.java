package com.zihler.fitness_tracker.application.outbound_ports.presenters;

import com.zihler.fitness_tracker.domain.values.WorkoutId;

public interface CopiedWorkoutPresenter {
    void present(WorkoutId copiedWorkoutId);
}
