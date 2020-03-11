package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.MuscleGroupRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.inputs.MuscleGroupFromDBInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.inputs.MuscleGroupsFromDBInput;
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
        return new MuscleGroupsFromDBInput(muscleGroups.findAll()).muscleGroups();
    }

    @Override
    public Exercise byName(ExerciseName exerciseName) {
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
