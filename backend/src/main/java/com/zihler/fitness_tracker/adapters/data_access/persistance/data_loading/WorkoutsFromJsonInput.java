package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.zihler.fitness_tracker.domain.values.Workouts;

import static java.util.stream.Collectors.toList;

public class WorkoutsFromJsonInput {
    private final WorkoutsJson json;

    public WorkoutsFromJsonInput(WorkoutsJson json) {
        this.json = json;
    }

    public Workouts getWorkouts() {
        return Workouts.of(json.getWorkouts()
                .stream()
                .map(WorkoutFromJsonInput::new)
                .map(WorkoutFromJsonInput::getWorkout)
                .collect(toList()));
    }
}
