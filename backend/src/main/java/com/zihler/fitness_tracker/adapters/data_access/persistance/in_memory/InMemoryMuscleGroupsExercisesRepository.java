package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.values.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Repository
public class InMemoryMuscleGroupsExercisesRepository
        implements
        FetchAllMuscleGroups,
        FetchMuscleGroup,
        StoreMuscleGroup,
        StoreMuscleGroups,
        FetchExercises,
        StoreExercises,
        FetchExercise,
        StoreExercise {
    private Map<Name, MuscleGroup> muscleGroupsAndExercises;

    public InMemoryMuscleGroupsExercisesRepository() {
        initMuscleGroupsAndExercises();
    }

    private void initMuscleGroupsAndExercises() {
        muscleGroupsAndExercises = new HashMap<>();
    }

    @Override
    public MuscleGroups fetchAll() {
        return MuscleGroups.of(muscleGroupsAndExercises.values().stream().filter(MuscleGroup::isSelectable).collect(toSet()));
    }

    @Override
    public MuscleGroups withValues(MuscleGroups muscleGroups) {
        muscleGroups.getMuscleGroups().forEach(muscleGroup -> muscleGroupsAndExercises.put(muscleGroup.getName(), muscleGroup));
        return muscleGroups;
    }

    @Override
    public Exercises forMuscleGroup(MuscleGroupName muscleGroupName) {
        return Exercises.of(find(muscleGroupName).getExercises().getExercises().stream().filter(Exercise::isSelectable).collect(toSet()));
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

    @Override
    public MuscleGroup withValues(MuscleGroup muscleGroup) {
        muscleGroupsAndExercises.put(muscleGroup.getName(), muscleGroup);
        return muscleGroup;
    }

    @Override
    public Exercise byName(ExerciseName exerciseName) {
        return muscleGroupsAndExercises.values()
                .stream()
                .map(MuscleGroup::getExercises)
                .map(Exercises::getExercises)
                .flatMap(Collection::stream)
                .filter(e -> e.getName().toString().equalsIgnoreCase(exerciseName.toString()))
                .findFirst()
                .get();
    }

    @Override
    public Exercise withValues(Exercise exercise) {

        MuscleGroup muscleGroup = muscleGroupsAndExercises.values()
                .stream()
                .filter(m -> m.getExercises().getExercises().contains(exercise))
                .findFirst()
                .get();


        return forMuscleGroup(muscleGroup.getName(), Exercises.of(Set.of(exercise)))
                .getExercises()
                .stream()
                .findFirst()
                .get();
    }
}
