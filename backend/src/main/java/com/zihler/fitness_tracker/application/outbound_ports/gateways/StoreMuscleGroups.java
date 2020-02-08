package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.values.MuscleGroups;

public interface StoreMuscleGroups {
    MuscleGroups withValues(MuscleGroups muscleGroups);
}
