package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.values.Workouts;

public interface FetchWorkouts {
    Workouts all();
}
