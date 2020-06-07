package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.entities.Exercise;

public interface StoreExercise {
    Exercise withValues(Exercise exercise);
}
