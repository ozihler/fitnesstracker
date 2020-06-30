package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.SetRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

import java.util.List;

public class WorkoutFromSqlInput {
    private final WorkoutRow row;
    private final List<SetRow> sets;

    public WorkoutFromSqlInput(WorkoutRow row, List<SetRow> sets) {
        this.row = row;
        this.sets = sets;
    }

    public Workout getWorkout() {
        return new Workout(
                WorkoutId.of(row.getWorkoutId()),
                CreationDate.of(row.getCreationDate()),
                new MuscleGroupsFromSqlInput(row.getMuscleGroups()).getMuscleGroups());
    }
}
