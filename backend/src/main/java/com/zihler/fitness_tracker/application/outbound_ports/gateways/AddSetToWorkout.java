package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public interface AddSetToWorkout {
    // todo return type does not make sense: Should be Workout instead, because the workout is updated. A set belongs to a workout foremost
    Set withValues(WorkoutId workoutId, Name exerciseName, Set setToStore);
}
