package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercises;

public interface FetchExercises {
    Exercises forMuscleGroup(MuscleGroup muscleGroup);
}
