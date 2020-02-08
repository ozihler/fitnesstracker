package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkouts;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.GenerateWorkoutId;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.Workouts;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryWorkoutRepository
        implements
        GenerateWorkoutId,
        FetchWorkout,
        FetchWorkouts,
        StoreWorkout {
    private Map<WorkoutId, Workout> workouts;

    public InMemoryWorkoutRepository() {
        workouts = new HashMap<>();
    }


    @Override
    public Workout as(Workout workout) {
        workouts.put(workout.getWorkoutId(), workout);
        return workout;
    }

    @Override
    public Workout by(WorkoutId id) {
        return workouts.get(id);
    }

    @Override
    public Workouts all() {
        return new Workouts(new ArrayList<>(workouts.values()));
    }

    private static int currentId = 1;
    private int userId = 1;

    @Override
    public WorkoutId next() {
        return WorkoutId.of(String.format("WORKOUT-%d-%d", userId, currentId++));
    }

}
