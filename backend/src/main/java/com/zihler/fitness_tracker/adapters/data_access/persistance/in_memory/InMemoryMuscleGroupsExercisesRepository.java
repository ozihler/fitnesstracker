package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.*;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Repository
public class InMemoryMuscleGroupsExercisesRepository
        implements
        FetchAllMuscleGroups,
        FetchMuscleGroups,
        FetchMuscleGroup,
        StoreMuscleGroups,
        FetchExercise,
        FetchExercises,
        StoreSet,
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
    public MuscleGroups as(MuscleGroups muscleGroups) {
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
    public Exercises in(Name muscleGroupName, Exercises exercises) {
        MuscleGroup muscleGroup = muscleGroupsAndExercises.get(muscleGroupName);

        if (muscleGroup == null) {
            muscleGroup = as(MuscleGroups.of(java.util.Set.of(new MuscleGroup(muscleGroupName)))).getMuscleGroups().iterator().next();
        }

        muscleGroup.add(exercises);

        muscleGroupsAndExercises.put(muscleGroup.getName(), muscleGroup);

        return exercises;
    }

    @Override
    public Exercise named(Name exerciseName) {

        return exerciseByName(exerciseName)
                .orElse(null);

    }

    private Optional<Exercise> exerciseByName(Name exerciseName) {
        return muscleGroupsAndExercises.values().stream()
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
    public MuscleGroups by(Names names) {
        return MuscleGroups.of(names.values()
                .stream()
                .map(name -> muscleGroupsAndExercises.get(name))
                .filter(Objects::nonNull)
                .collect(toSet())
        );
    }


}
