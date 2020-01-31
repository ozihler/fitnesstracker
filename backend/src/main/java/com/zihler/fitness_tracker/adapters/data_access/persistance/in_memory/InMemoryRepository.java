package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.StoreExercises;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.GID;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Name;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class InMemoryRepository
        implements
        FetchWorkout,
        StoreWorkout,
        FetchAllMuscleGroups,
        StoreMuscleGroups,
        FetchMuscleGroup,
        FetchExercises,
        StoreExercises {
    private Map<Name, MuscleGroup> repo;
    private Map<GID, Workout> workouts = new HashMap<>();

    public InMemoryRepository() {
        this.repo = new HashMap<>();
    }

    @Override
    public Workout as(Workout workout) {
        this.workouts.put(workout.getGid(), workout);
        if (workout.getMuscleGroups() != null) {
            MuscleGroups muscleGroups = workout.getMuscleGroups();
            for (MuscleGroup muscleGroup : muscleGroups.getMuscleGroups()) {
                repo.put(muscleGroup.getName(), muscleGroup);
            }
        }
        return workout;
    }

    @Override
    public Workout by(GID id) {
        return workouts.get(id);
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

    @Override
    public Exercises in(Name muscleGroupName, Exercises exercises) {
        MuscleGroup muscleGroup = repo.get(muscleGroupName);

        if (muscleGroup == null) {
            muscleGroup = this.as(MuscleGroups.of(Set.of(new MuscleGroup(muscleGroupName)))).getMuscleGroups().iterator().next();
        }

        muscleGroup.add(exercises);

        this.repo.put(muscleGroup.getName(), muscleGroup);

        return exercises;
    }
}
