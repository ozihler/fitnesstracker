package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Names;

public interface FetchMuscleGroups {
    MuscleGroups by(Names names);
}
