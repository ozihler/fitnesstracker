package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.musclegroups;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroupName;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Repository
@Profile("e2etest")
public class InMemoryMuscleGroupsExercisesRepository
        implements
        FetchAllMuscleGroups,
        FetchMuscleGroup,
        StoreMuscleGroup,
        StoreMuscleGroups,
        FetchExercises,
        StoreExercises,
        FetchExercise,
        UpdateExistingExercise {
    private static final Logger logger = LoggerFactory.getLogger(InMemoryMuscleGroupsExercisesRepository.class);

    private Map<Name, MuscleGroup> muscleGroupsAndExercises;

    @Autowired
    public InMemoryMuscleGroupsExercisesRepository() {
        initMuscleGroupsAndExercises();
    }

    public void initMuscleGroupsAndExercises() {
        muscleGroupsAndExercises = new HashMap<>();
    }

    @Override
    public MuscleGroups fetchAll() {
        return MuscleGroups.of(muscleGroupsAndExercises.values().stream().filter(MuscleGroup::isSelectable).collect(toList()));
    }

    @Override
    public MuscleGroups withValues(MuscleGroups muscleGroups) {
        muscleGroups.getMuscleGroups().forEach(this::withValues);
        return muscleGroups;
    }

    @Override
    public Exercises forMuscleGroup(MuscleGroupName muscleGroupName) {
        return Exercises.of(find(muscleGroupName).getExercises().getExercises().stream().filter(Exercise::isSelectable).collect(toList()));
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
    public Exercises ofMuscleGroup(Name muscleGroupName, Exercises exercises) {
        MuscleGroup muscleGroup = muscleGroupsAndExercises.get(muscleGroupName);

        if (muscleGroup == null) {
            muscleGroup = new MuscleGroup(muscleGroupName, exercises);
        } else {
            muscleGroup.add(exercises);
        }

        withValues(muscleGroup);

        return exercises;
    }

    @Override
    public MuscleGroup withValues(MuscleGroup muscleGroup) {
        muscleGroupsAndExercises.put(muscleGroup.getName(), muscleGroup);
        MuscleGroups toStore = new MuscleGroups(new ArrayList<>(muscleGroupsAndExercises.values()));
        return muscleGroup;
    }

    @Override
    public Exercise byName(Name exerciseName) {
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


        return ofMuscleGroup(muscleGroup.getName(), Exercises.of(List.of(exercise)))
                .getExercises()
                .stream()
                .findFirst()
                .get();
    }
}
