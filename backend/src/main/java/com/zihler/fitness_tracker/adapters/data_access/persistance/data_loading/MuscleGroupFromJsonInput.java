package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;

public class MuscleGroupFromJsonInput {
    private final MuscleGroupJson json;

    public MuscleGroupFromJsonInput(MuscleGroupJson json) {
        this.json = json;
    }

    public MuscleGroup getMuscleGroup() {
        return new MuscleGroup(
                Name.of(json.getName()),
                new ExercisesFromJsonInput(json.getExercises()).getExercises()
        );
    }
}
