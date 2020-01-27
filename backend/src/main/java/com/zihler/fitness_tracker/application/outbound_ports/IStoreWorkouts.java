package com.zihler.fitness_tracker.application.outbound_ports;

import com.zihler.fitness_tracker.domain.entities.Workout;

public interface IStoreWorkouts {
    Workout store(Workout workout);
}
