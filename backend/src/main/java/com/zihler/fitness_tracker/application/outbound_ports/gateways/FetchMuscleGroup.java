package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;

public interface FetchMuscleGroup {
    MuscleGroup byName(Name name);
}
