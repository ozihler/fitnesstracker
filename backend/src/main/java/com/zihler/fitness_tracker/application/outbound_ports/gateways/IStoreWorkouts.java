package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.entities.Workout;

public interface IStoreWorkouts {
    Workout store(Workout workout);
}
