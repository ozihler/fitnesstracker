package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.values.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Repository
public class InMemoryMuscleGroupsExercisesRepository
        implements
        FetchAllMuscleGroups,
        FetchMuscleGroup,
        StoreMuscleGroups,
        FetchExercises,
        StoreExercises {
    private Map<Name, MuscleGroup> muscleGroupsAndExercises;

    public InMemoryMuscleGroupsExercisesRepository() {
        initMuscleGroupsAndExercises();
    }

    private void initMuscleGroupsAndExercises() {
        muscleGroupsAndExercises = new HashMap<>();
    }

    @Override
    public MuscleGroups fetchAll() {
        return MuscleGroups.of(new HashSet<>(muscleGroupsAndExercises.values()));
    }

    @Override
    public MuscleGroups withValues(MuscleGroups muscleGroups) {
        muscleGroups.getMuscleGroups().forEach(muscleGroup -> muscleGroupsAndExercises.put(muscleGroup.getName(), muscleGroup));
        return muscleGroups;
    }

    @Override
    public Exercises forMuscleGroup(MuscleGroupName muscleGroupName) {
        return find(muscleGroupName).getExercises();
    }

    private MuscleGroup find(MuscleGroupName muscleGroupName) {

        return muscleGroupsAndExercises.values()
                .stream()
                .filter(muscleGroup1 -> muscleGroupName.toString().equals(muscleGroup1.getName().toString()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find Muscle Group with name " + muscleGroupName.toString()));
    }

    @Override
    public MuscleGroup by(Name name) {
        return muscleGroupsAndExercises.get(name);
    }

    @Override
    public Exercises forMuscleGroup(Name muscleGroupName, Exercises exercises) {
        MuscleGroup muscleGroup = muscleGroupsAndExercises.get(muscleGroupName);

        if (muscleGroup == null) {
            muscleGroup = withValues(MuscleGroups.of(java.util.Set.of(new MuscleGroup(muscleGroupName)))).getMuscleGroups().iterator().next();
        }

        muscleGroup.add(exercises);

        muscleGroupsAndExercises.put(muscleGroup.getName(), muscleGroup);

        return exercises;
    }

}
