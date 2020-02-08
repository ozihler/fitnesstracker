package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroupName;

public interface FetchExercises {
    Exercises forMuscleGroup(MuscleGroupName muscleGroupName);
}
