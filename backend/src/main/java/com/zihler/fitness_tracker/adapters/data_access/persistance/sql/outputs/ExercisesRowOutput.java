package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading.ExerciseJson;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExercisesRowOutput {
    private final List<ExerciseJson> exercises;

    public ExercisesRowOutput(List<ExerciseJson> exercises) {
        this.exercises = exercises;
    }

    public List<ExerciseRow> getRows() {
        return exercises.stream()
                .map(ExerciseRowOutput::new)
                .map(ExerciseRowOutput::getRow)
                .collect(toList());
    }
}
