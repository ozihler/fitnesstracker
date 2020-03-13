package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.rowtypes.ExerciseRow;
import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.Exercises;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExercisesFromDBInput {
    private List<ExerciseRow> exercises;

    public ExercisesFromDBInput(List<ExerciseRow> exercises) {
        this.exercises = exercises;
    }

    public Exercises exercises() {

        List<Exercise> exercises = this.exercises.stream()
                .filter(ExerciseRow::isSelectable)
                .map(ExerciseFromDBInput::new)
                .map(ExerciseFromDBInput::exercise)
                .collect(toList());

        return Exercises.of(exercises);
    }
}
