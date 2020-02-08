package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public interface StoreSet {
    Set withValues(WorkoutId workoutId, Name exerciseName, Set setToStore);
}
