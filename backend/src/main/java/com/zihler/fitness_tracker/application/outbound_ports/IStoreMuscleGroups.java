package com.zihler.fitness_tracker.application.outbound_ports;

import com.zihler.fitness_tracker.domain.values.MuscleGroups;

public interface IStoreMuscleGroups {
    MuscleGroups as(MuscleGroups muscleGroups);
}
