package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.ExerciseRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.MuscleGroupRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.inputs.ExerciseFromDBInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.inputs.ExercisesFromDBInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.inputs.MuscleGroupFromDBInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.inputs.MuscleGroupsFromDBInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.outputs.ExerciseToDBOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.outputs.ExercisesToDBOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.outputs.MuscleGroupToDBOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.outputs.MuscleGroupsToDBOutput;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.values.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    private JpaMuscleGroupsRepository muscleGroups;
    private JpaExercisesRepository exercises;

    @Autowired
    public SqlMuscleGroupsExercisesRepository(JpaMuscleGroupsRepository muscleGroups, JpaExercisesRepository exercises) {
        this.muscleGroups = muscleGroups;
        this.exercises = exercises;
    }

    @Override
    public MuscleGroups fetchAll() {
        var rows = muscleGroups.findAll();

        var input = new MuscleGroupsFromDBInput(rows);

        return input.muscleGroups();
    }

    @Override
    public Exercise byName(ExerciseName exerciseName) {
        var row = this.exercises.findByNameOrThrow(exerciseName.toString());

        var input = new ExerciseFromDBInput(row);

        return input.exercise();
    }

    @Override
    public Exercises forMuscleGroup(MuscleGroupName muscleGroupName) {
        var rows = exercises.findByMuscleGroup(muscleGroupName.toString());

        var input = new ExercisesFromDBInput(rows);

        return input.exercises();
    }

    @Override
    public MuscleGroup by(Name name) {
        var row = muscleGroups.findByNameOnlySelectableOrThrow(name.toString());

        var input = new MuscleGroupFromDBInput(row);

        return input.muscleGroup();
    }

    @Override
    public Exercise withValues(Exercise exercise) {
        var originalRow = this.exercises.findByNameOrThrow(exercise.getName().toString());

        var output = new ExerciseToDBOutput(exercise, originalRow.getMuscleGroup());

        var rowToSave = output.exerciseRow();

        var savedRow = exercises.save(rowToSave);

        var input = new ExerciseFromDBInput(savedRow);

        return input.exercise();
    }

    @Override
    public Exercises forMuscleGroup(Name muscleGroupName, Exercises exercises) {
        MuscleGroupRow muscleGroupRow = this.muscleGroups.findByNameOnlySelectableOrThrow(muscleGroupName.toString());

        List<Exercise> exercisesToStore = exercises.getExercises();

        var output = new ExercisesToDBOutput(exercisesToStore, muscleGroupRow);

        List<ExerciseRow> storedExercises = this.exercises.saveAll(output.exercises());

        ExercisesFromDBInput input = new ExercisesFromDBInput(storedExercises);

        return input.exercises();
    }

    @Override
    public MuscleGroup withValues(MuscleGroup muscleGroup) {
        var output = new MuscleGroupToDBOutput(muscleGroup);

        var muscleGroupToStore = output.muscleGroupRow();

        var storedMuscleGroup = this.muscleGroups.save(muscleGroupToStore);

        var input = new MuscleGroupFromDBInput(storedMuscleGroup);

        return input.muscleGroup();
    }

    @Override
    public MuscleGroups withValues(MuscleGroups muscleGroups) {
        var output = new MuscleGroupsToDBOutput(muscleGroups);

        var muscleGroupsToStore = output.muscleGroupRows();

        var storedMuscleGroups = this.muscleGroups.saveAll(muscleGroupsToStore);

        var input = new MuscleGroupsFromDBInput(storedMuscleGroups);

        return input.muscleGroups();
    }

}
