package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import com.zihler.fitness_tracker.domain.entities.Workout;

public class WorkoutToSqlOutput {
    private final Workout workout;

    public WorkoutToSqlOutput(Workout workout) {
        this.workout = workout;
    }

    public WorkoutRow getRow() {
        var row = new WorkoutRow();
        row.setDeleted(workout.isDeleted());
        row.setCreationDate(workout.getCreationDate().toLocalDate());
        row.setWorkoutId(workout.getWorkoutId().toString());
        // no muscle groups here because we need to see if muscle groups already exist or not
        return row;
    }
}
