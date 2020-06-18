package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.Workouts;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("prod")
public class SqlWorkoutRepository implements
        GenerateWorkoutId,
        FetchWorkout,
        FetchWorkouts,
        StoreWorkout,
        StoreSet {
    @Override
    public Workout by(WorkoutId id) {
        return null;
    }

    @Override
    public Workouts all() {
        return null;
    }

    @Override
    public WorkoutId next() {
        return null;
    }

    @Override
    public Set withValues(WorkoutId workoutId, Name exerciseName, Set setToStore) {
        return null;
    }

    @Override
    public Workout withValues(Workout workout) {
        return null;
    }
}
