package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

import java.util.Objects;

public class Workout {
    private final WorkoutId workoutId;
    private final CreationDate creationDate;
    private final MuscleGroups muscleGroups;
    private boolean isDeleted;

    public Workout(WorkoutId workoutId, CreationDate creationDate, MuscleGroups muscleGroups) {
        this.workoutId = workoutId;
        this.creationDate = Objects.requireNonNullElseGet(creationDate, CreationDate::now);
        this.muscleGroups = Objects.requireNonNullElseGet(muscleGroups, MuscleGroups::new);
        this.isDeleted = false;
    }

    public WorkoutId getWorkoutId() {
        return workoutId;
    }

    public CreationDate getCreationDate() {
        return creationDate;
    }

    public MuscleGroups getMuscleGroups() {
        return muscleGroups;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutId=" + workoutId +
                ", creationDate=" + creationDate +
                ", muscleGroups=" + muscleGroups +
                '}';
    }
}
