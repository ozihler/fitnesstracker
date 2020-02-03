package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.StoreExercises;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.GID;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Name;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryRepository
        implements
        FetchWorkout,
        StoreWorkout,
        FetchAllMuscleGroups,
        FetchMuscleGroup,
        StoreMuscleGroups,
        FetchExercise,
        FetchExercises,
        StoreSet,
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
            muscleGroup = this.as(MuscleGroups.of(java.util.Set.of(new MuscleGroup(muscleGroupName)))).getMuscleGroups().iterator().next();
        }

        muscleGroup.add(exercises);

        this.repo.put(muscleGroup.getName(), muscleGroup);

        return exercises;
    }

    @Override
    public Exercise named(Name exerciseName) {

        return exerciseByName(exerciseName)
                .orElse(null);

    }

    private Optional<Exercise> exerciseByName(Name exerciseName) {
        return this.repo.values().stream()
                .map(MuscleGroup::getExercises)
                .map(Exercises::getExercises)
                .flatMap(Collection::stream)
                .filter(e -> e.getName().equals(exerciseName))
                .findFirst();
    }

    @Override
    public Set withValues(Name exerciseName, Set setToStore) {
        exerciseByName(exerciseName).get().getSets().add(setToStore);
        return setToStore;
    }
}