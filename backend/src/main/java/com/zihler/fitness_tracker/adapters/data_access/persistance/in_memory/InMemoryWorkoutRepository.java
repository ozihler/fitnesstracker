package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.IStoreWorkouts;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.GID;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryWorkoutRepository implements IStoreWorkouts {
    private Map<GID, Workout> workouts;

    public InMemoryWorkoutRepository() {
        this.workouts = new HashMap<>();
    }

    @Override
    public Workout store(Workout workout) {
        this.workouts.put(workout.getId(), workout);
        return workout;
    }
}
