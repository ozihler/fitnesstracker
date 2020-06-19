package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.values.Multiplier;
import com.zihler.fitness_tracker.domain.values.Name;

public class ExerciseFromJsonInput {
    private final ExerciseJson json;

    public ExerciseFromJsonInput(ExerciseJson json) {
        this.json = json;
    }

    public Exercise getExercise() {
        return new Exercise(
                Name.of(json.getName()),
                new SetsFromJsonInput(json.getSets()).getSets(),
                Multiplier.of(json.getMultiplier())
        );
    }
}
