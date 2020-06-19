package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class WorkoutFromSqlInput {
    private final WorkoutRow row;

    public WorkoutFromSqlInput(WorkoutRow row) {
        this.row = row;
    }

    public Workout getWorkout() {
        return new Workout(
                WorkoutId.of(row.getWorkoutId()),
                CreationDate.of(row.getCreationDate()),
                new MuscleGroupsFromSqlInput(row.getMuscleGroups()).getMuscleGroups());
    }
}
