package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.rowtypes.WorkoutRow;
import com.zihler.fitness_tracker.domain.entities.Workout;

public class WorkoutFromDBInput {
    private WorkoutRow row;

    public WorkoutFromDBInput(WorkoutRow row) {
        this.row = row;
    }

    public Workout workout() {
        return null;
    }
}
