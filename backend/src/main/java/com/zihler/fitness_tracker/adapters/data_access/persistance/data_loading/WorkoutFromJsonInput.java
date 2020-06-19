package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class WorkoutFromJsonInput {
    private final WorkoutJson json;

    public WorkoutFromJsonInput(WorkoutJson json) {
        this.json = json;
    }

    public Workout getWorkout() {
        return new Workout(
                WorkoutId.of(json.getWorkoutId()),
                CreationDate.of(json.getCreationDate()),
                new MuscleGroupsFromJsonInput(json.getMuscleGroups()).getMuscleGroups()
        );
    }
}
