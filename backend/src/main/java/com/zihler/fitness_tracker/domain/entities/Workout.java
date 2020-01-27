package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.GID;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.ZonedDateTime;

public class Workout {
    private final GID id;
    private final ZonedDateTime creationDateTime;
    private WorkoutTitle workoutTitle;

    public Workout(WorkoutTitle workoutTitle) {
        this.id = GID.random();
        this.creationDateTime = ZonedDateTime.now();
        this.workoutTitle = workoutTitle;
    }

    public GID getId() {
        return id;
    }

    public ZonedDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public WorkoutTitle getWorkoutTitle() {
        return workoutTitle;
    }
}
