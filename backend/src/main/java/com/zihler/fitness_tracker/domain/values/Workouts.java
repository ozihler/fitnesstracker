package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Workout;

import java.util.List;

public class Workouts {
    private List<Workout> workouts;

    private Workouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public static Workouts of(List<Workout> workouts) {
        return new Workouts(workouts);
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }
}
