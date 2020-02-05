package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.Name;

public interface StoreExercises {
    Exercises in(Name muscleGroupName, Exercises exercises);
}
