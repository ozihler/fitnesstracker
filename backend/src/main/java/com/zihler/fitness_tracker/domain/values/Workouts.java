package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Workout;

import java.util.List;

public class Workouts {
    private List<Workout> workouts;

    public Workouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }
}
