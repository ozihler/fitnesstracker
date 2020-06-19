package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.exceptions.ExerciseNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.exceptions.MuscleGroupNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs.*;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
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

    private final JpaMuscleGroupsRepository muscleGroupsRepository;
    private final JpaExerciseRepository exerciseRepository;

    @Autowired
    public SqlMuscleGroupsExercisesRepository(
            JpaMuscleGroupsRepository muscleGroupsRepository,
            JpaExerciseRepository exerciseRepository) {
        this.muscleGroupsRepository = muscleGroupsRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public MuscleGroups fetchAll() {
        var muscleGroupRows = muscleGroupsRepository.findAll();

        var input = new MuscleGroupsFromSqlInput(muscleGroupRows);

        return input.getMuscleGroups();
    }

    @Override
    public Exercise byName(Name exerciseName) {
        return this.exerciseRepository.findByName(exerciseName.toString())
                .map(ExerciseFromSqlInput::new)
                .map(ExerciseFromSqlInput::getExercise)
                .orElseThrow(() -> new ExerciseNotFoundException("Could not find Exercise with name " + exerciseName.toString()));
    }

    @Override
    public Exercises forMuscleGroup(MuscleGroupName muscleGroupName) {
        return muscleGroupsRepository.findByName(muscleGroupName.toString())
                .map(MuscleGroupRow::getExercises)
                .map(ExercisesFromSqlInput::new)
                .map(ExercisesFromSqlInput::getExercises)
                .orElseThrow(() -> new MuscleGroupNotFoundException("Could not find Muscle Group with name " + muscleGroupName.toString()));
    }

    @Override
    public MuscleGroup by(Name name) {
        return muscleGroupsRepository.findByName(name.toString())
                .map(MuscleGroupFromSqlInput::new)
                .map(MuscleGroupFromSqlInput::getMuscleGroup)
                .orElseThrow(() -> new MuscleGroupNotFoundException("Could not find Muscle Group with name " + name.toString()));
    }


    @Override
    public Exercise withValues(Exercise exercise) {
        new ExerciseRowInput(exercise).getRow();
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
