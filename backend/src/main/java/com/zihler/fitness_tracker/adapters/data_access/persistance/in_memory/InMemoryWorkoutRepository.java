package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.GID;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryWorkoutRepository implements FetchWorkout, StoreWorkout {
    private Map<GID, Workout> workouts;

    public InMemoryWorkoutRepository() {
        this.workouts = new HashMap<>();
    }

    @Override
    public Workout as(Workout workout) {
        this.workouts.put(workout.getGid(), workout);
        return workout;
    }

    @Override
    public Workout by(GID id) {
        return workouts.get(id);
    }
}
