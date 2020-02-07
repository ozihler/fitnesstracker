package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.values.WorkoutId;

public interface GenerateWorkoutId {
    WorkoutId next();
}
