package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.values.MuscleGroup;

public interface StoreMuscleGroup {
    MuscleGroup withValues(MuscleGroup muscleGroup);
}
