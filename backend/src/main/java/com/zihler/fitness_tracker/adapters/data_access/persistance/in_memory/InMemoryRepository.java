package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Repository
public class InMemoryRepository
        implements
        GenerateWorkoutId,
        FetchWorkout,
        FetchWorkouts,
        StoreWorkout,
        FetchAllMuscleGroups,
        FetchMuscleGroups,
        FetchMuscleGroup,
        StoreMuscleGroups,
        FetchExercise,
        FetchExercises,
        StoreSet,
        StoreExercises {
    private Map<Name, MuscleGroup> repo;
    private Map<WorkoutId, Workout> workouts = new HashMap<>();

    public InMemoryRepository() {
        repo = new HashMap<>();
    }

    @Override
    public Workout as(Workout workout) {
        workouts.put(workout.getWorkoutId(), workout);
        if (workout.getMuscleGroups() != null) {
            MuscleGroups muscleGroups = workout.getMuscleGroups();
            for (MuscleGroup muscleGroup : muscleGroups.getMuscleGroups()) {
                repo.put(muscleGroup.getName(), muscleGroup);
            }
        }
        return workout;
    }

    @Override
    public Workout by(WorkoutId id) {
        return workouts.get(id);
    }

    @Override
    public MuscleGroups fetchAll() {
        return MuscleGroups.of(new HashSet<>(repo.values()));
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
    public MuscleGroup by(Name name) {
        return repo.get(name);
    }

    @Override
    public Exercises in(Name muscleGroupName, Exercises exercises) {
        MuscleGroup muscleGroup = repo.get(muscleGroupName);

        if (muscleGroup == null) {
            muscleGroup = as(MuscleGroups.of(java.util.Set.of(new MuscleGroup(muscleGroupName)))).getMuscleGroups().iterator().next();
        }

        muscleGroup.add(exercises);

        repo.put(muscleGroup.getName(), muscleGroup);

        return exercises;
    }

    @Override
    public Exercise named(Name exerciseName) {

        return exerciseByName(exerciseName)
                .orElse(null);

    }

    private Optional<Exercise> exerciseByName(Name exerciseName) {
        return repo.values().stream()
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

    @Override
    public Workouts all() {
        return new Workouts(new ArrayList<>(workouts.values()));
    }

    @Override
    public MuscleGroups by(Names names) {
        return MuscleGroups.of(names.values()
                .stream()
                .map(name -> repo.get(name))
                .filter(Objects::nonNull)
                .collect(toSet())
        );
    }

    private static int currentId = 1;
    private int userId = 1;


    @Override
    public WorkoutId next() {
        return WorkoutId.of(String.format("WORKOUT-%d-%d", userId, currentId++));
    }
}
