package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.entities.MuscleGroup;

public interface StoreMuscleGroup {
    MuscleGroup withValues(MuscleGroup muscleGroup);
}
