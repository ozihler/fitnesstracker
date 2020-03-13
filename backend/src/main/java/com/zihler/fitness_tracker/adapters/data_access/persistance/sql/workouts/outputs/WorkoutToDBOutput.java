package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.rowtypes.WorkoutRow;
import com.zihler.fitness_tracker.domain.entities.Workout;

public class WorkoutToDBOutput {
    private Workout workout;

    public WorkoutToDBOutput(Workout workout) {
        this.workout = workout;
    }

    public WorkoutRow row() {
        WorkoutRow workoutRow = new WorkoutRow();


        return workoutRow;
    }
}
