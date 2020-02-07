package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.ZonedDateTime;

public class Workout {
    private final WorkoutId workoutId;
    private final ZonedDateTime creationDateTime;
    private WorkoutTitle workoutTitle;
    private MuscleGroups muscleGroups;

    private Workout(WorkoutTitle workoutTitle, WorkoutId workoutId) {
        this(workoutId, ZonedDateTime.now(), workoutTitle, new MuscleGroups());
    }

    public static Workout newWorkout(WorkoutTitle workoutTitle, WorkoutId workoutId) {
        return new Workout(workoutTitle, workoutId);
    }

    public static Workout update(WorkoutId workoutId, ZonedDateTime creationDateTime, WorkoutTitle workoutTitle, MuscleGroups muscleGroups) {
        return new Workout(workoutId, creationDateTime, workoutTitle, muscleGroups);
    }

    public Workout(WorkoutId workoutId, ZonedDateTime creationDateTime, WorkoutTitle workoutTitle, MuscleGroups muscleGroups) {
        this.workoutId = workoutId;
        this.creationDateTime = creationDateTime;
        this.workoutTitle = workoutTitle;
        this.muscleGroups = muscleGroups;
    }

    public WorkoutId getWorkoutId() {
        return workoutId;
    }

    public ZonedDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public WorkoutTitle getWorkoutTitle() {
        return workoutTitle;
    }

    public MuscleGroups getMuscleGroups() {
        return muscleGroups;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "gid=" + workoutId +
                ", creationDateTime=" + creationDateTime +
                ", workoutTitle=" + workoutTitle +
                ", muscleGroups=" + muscleGroups +
                '}';
    }

}
