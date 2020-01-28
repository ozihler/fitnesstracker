package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.IFetchMuscleGroups;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Name;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Repository
public class InMemoryMuscleGroupsRepository implements IFetchMuscleGroups {
    private Map<Name, MuscleGroup> muscleGroups;

    public InMemoryMuscleGroupsRepository() {
        this.muscleGroups = new HashMap<>();
    }

    @Override
    public MuscleGroups fetchAll() {
        return MuscleGroups.of(new HashSet<>(this.muscleGroups.values()));
    }
}
