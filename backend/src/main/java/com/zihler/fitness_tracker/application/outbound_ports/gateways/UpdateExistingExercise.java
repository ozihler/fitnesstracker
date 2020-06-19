package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.entities.Exercise;

public interface UpdateExistingExercise {
    Exercise withValues(Exercise exercise);
}
