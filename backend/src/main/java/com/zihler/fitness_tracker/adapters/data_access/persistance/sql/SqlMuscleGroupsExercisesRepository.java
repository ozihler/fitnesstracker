package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs.ExerciseFromSqlInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs.ExercisesFromSqlInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs.MuscleGroupFromSqlInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs.MuscleGroupsFromSqlInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs.ExercisesToSqlOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs.MuscleGroupToSqlOutput;
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

import java.util.stream.Collectors;

@Repository
@Profile("prod")
public class SqlMuscleGroupsExercisesRepository implements
        FetchAllMuscleGroups,
        FetchMuscleGroup,
        StoreMuscleGroup,
        StoreMuscleGroups,
        FetchExercises,
        FetchExercise,
        StoreExercises,
        UpdateExistingExercise {
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
        return new ExerciseFromSqlInput(this.exerciseRepository.findByNameOrThrow(exerciseName.toString()))
                .getExercise();
    }

    @Override
    public Exercises forMuscleGroup(MuscleGroupName muscleGroupName) {
        var muscleGroupRow = muscleGroupsRepository.findByNameOrThrow(muscleGroupName.toString());
        return new ExercisesFromSqlInput(muscleGroupRow.getExercises())
                .getExercises();
    }

    @Override
    public MuscleGroup by(Name muscleGroupName) {
        var muscleGroupRow = muscleGroupsRepository.findByNameOrThrow(muscleGroupName.toString());
        return new MuscleGroupFromSqlInput(muscleGroupRow)
                .getMuscleGroup();
    }


    @Override
    public Exercise withValues(Exercise exercise) {
        var exerciseName = exercise.getName().toString();
        var rowToUpdate = exerciseRepository.findByNameOrThrow(exerciseName);

        rowToUpdate.setName(exerciseName);
        rowToUpdate.setSelectable(exercise.isSelectable());
        rowToUpdate.setMultiplier(exercise.getMultiplier().value());

        var updatedRow = exerciseRepository.save(rowToUpdate);

        return new ExerciseFromSqlInput(updatedRow)
                .getExercise();
    }

    @Override
    public Exercises ofMuscleGroup(Name muscleGroupName, Exercises exercises) {
        var muscleGroupRowToAppendExercises = this.muscleGroupsRepository.findByNameOrThrow(muscleGroupName.toString());
        var exerciseRowsToStore = new ExercisesToSqlOutput(muscleGroupRowToAppendExercises, exercises.getExercises()).getRows();
        var storedExerciseRows = this.exerciseRepository.saveAll(exerciseRowsToStore);
        return new ExercisesFromSqlInput(storedExerciseRows).getExercises();
    }

    @Override
    public MuscleGroup withValues(MuscleGroup muscleGroup) {
        var muscleGroupRowOptional = muscleGroupsRepository.findByName(muscleGroup.getName().toString());
        if (muscleGroupRowOptional.isEmpty()) {
            return createNewMuscleGroup(muscleGroup);
        } else {
            return updateMuscleGroup(muscleGroupRowOptional.get(), muscleGroup);
        }
    }

    private MuscleGroup createNewMuscleGroup(MuscleGroup muscleGroup) {
        var row = new MuscleGroupToSqlOutput(muscleGroup).getRow();
        var savedRow = muscleGroupsRepository.save(row);
        return new MuscleGroupFromSqlInput(savedRow).getMuscleGroup();
    }

    private MuscleGroup updateMuscleGroup(MuscleGroupRow muscleGroupRow, MuscleGroup newData) {
        muscleGroupRow.setName(newData.getName().toString());
        muscleGroupRow.setSelectable(newData.isSelectable());
        muscleGroupRow.setExercises(new ExercisesToSqlOutput(muscleGroupRow, newData.getExercises().getExercises()).getRows());
        return newData;
    }

    @Override
    public MuscleGroups withValues(MuscleGroups muscleGroups) {
        return MuscleGroups.of(muscleGroups.getMuscleGroups()
                .stream()
                .map(this::withValues)
                .collect(Collectors.toList()));
    }
}
