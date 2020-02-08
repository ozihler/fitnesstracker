package com.zihler.fitness_tracker.application.outbound_ports.presenters;

import com.zihler.fitness_tracker.domain.values.ExerciseName;

public interface ExerciseNamePresenter {
    void present(ExerciseName exerciseName);
}
