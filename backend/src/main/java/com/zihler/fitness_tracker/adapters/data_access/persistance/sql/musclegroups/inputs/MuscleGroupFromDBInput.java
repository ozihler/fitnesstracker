package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.rowtypes.MuscleGroupRow;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;

public class MuscleGroupFromDBInput {
    private MuscleGroupRow muscleGroupRow;

    public MuscleGroupFromDBInput(MuscleGroupRow muscleGroupRow) {
        this.muscleGroupRow = muscleGroupRow;
    }

    public MuscleGroup muscleGroup() {
        return   MuscleGroup.of(name(), exercises());
    }

    private Name name() {
        return Name.of(muscleGroupRow.getName());
    }

    private Exercises exercises() {
        return new ExercisesFromDBInput(muscleGroupRow.getExercises()).exercises();
    }

}
