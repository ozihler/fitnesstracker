package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.inputs.WorkoutFromDBInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.rowtypes.WorkoutRow;
import com.zihler.fitness_tracker.domain.values.Workouts;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class WorkoutsFromDBInput {
    private List<WorkoutRow> rows;

    public WorkoutsFromDBInput(List<WorkoutRow> rows) {
        this.rows = rows;
    }

    public Workouts workouts() {
        return Workouts.of(
                rows.stream()
                        .map(WorkoutFromDBInput::new)
                        .map(WorkoutFromDBInput::workout)
                        .collect(toList())
        );
    }
}
