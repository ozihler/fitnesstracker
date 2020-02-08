package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.ExerciseName;

public interface FetchExercise {
    Exercise byName(ExerciseName exerciseName);
}
