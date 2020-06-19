package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import com.zihler.fitness_tracker.domain.values.Workouts;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class WorkoutsFromSqlInput {
    private final List<WorkoutRow> rows;

    public WorkoutsFromSqlInput(List<WorkoutRow> rows) {
        this.rows = rows;
    }

    public Workouts getWorkouts() {
        return Workouts.of(rows.stream()
                .map(WorkoutFromSqlInput::new)
                .map(WorkoutFromSqlInput::getWorkout)
                .collect(toList()));
    }
}
