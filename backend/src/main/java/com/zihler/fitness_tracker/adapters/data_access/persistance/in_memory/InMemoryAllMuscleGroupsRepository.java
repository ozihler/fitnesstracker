package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercises;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreMuscleGroups;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchAllMuscleGroups;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Name;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Repository
public class InMemoryAllMuscleGroupsRepository implements FetchAllMuscleGroups, StoreMuscleGroups, FetchMuscleGroup, FetchExercises {
    private Map<Name, MuscleGroup> repo;

    public InMemoryAllMuscleGroupsRepository() {
        this.repo = new HashMap<>();
    }

    @Override
    public MuscleGroups fetchAll() {
        return MuscleGroups.of(new HashSet<>(this.repo.values()));
    }

    @Override
    public MuscleGroups as(MuscleGroups muscleGroups) {
        muscleGroups.getMuscleGroups().forEach(muscleGroup -> repo.put(muscleGroup.getName(), muscleGroup));
        return muscleGroups;
    }

    @Override
    public Exercises forMuscleGroup(MuscleGroup muscleGroup) {
        return find(muscleGroup).getExercises();
    }

    private MuscleGroup find(MuscleGroup muscleGroup) {
        return repo.values()
                .stream()
                .filter(muscleGroup1 -> muscleGroup.getName().equals(muscleGroup1.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find Muscle Group with name " + muscleGroup));
    }

    @Override
    public MuscleGroup byName(Name name) {
        return repo.get(name);
    }
}
