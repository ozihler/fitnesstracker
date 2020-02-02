package com.zihler.fitness_tracker.application.outbound_ports.gateways;

import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.Name;

public interface StoreSet {
    Set withValues(Name exerciseName, Set setToStore);
}
