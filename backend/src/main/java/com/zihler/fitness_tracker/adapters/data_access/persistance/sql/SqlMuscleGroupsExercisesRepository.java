package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroupName;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("prod")
public class SqlMuscleGroupsExercisesRepository implements
        FetchAllMuscleGroups,
        FetchMuscleGroup,
        StoreMuscleGroup,
        StoreMuscleGroups,
        FetchExercises,
        StoreExercises,
        FetchExercise,
        StoreExercise {
    private static final Logger logger = LoggerFactory.getLogger(SqlMuscleGroupsExercisesRepository.class);

    @Override
    public MuscleGroups fetchAll() {
        return null;
    }

    @Override
    public Exercise byName(Name exerciseName) {
        return null;
    }

    @Override
    public Exercises forMuscleGroup(MuscleGroupName muscleGroupName) {
        return null;
    }

    @Override
    public MuscleGroup by(Name name) {
        return null;
    }

    @Override
    public Exercise withValues(Exercise exercise) {
        return null;
    }

    @Override
    public Exercises forMuscleGroup(Name muscleGroupName, Exercises exercises) {
        return null;
    }

    @Override
    public MuscleGroup withValues(MuscleGroup muscleGroup) {
        return null;
    }

    @Override
    public MuscleGroups withValues(MuscleGroups muscleGroups) {
        return null;
    }
}
