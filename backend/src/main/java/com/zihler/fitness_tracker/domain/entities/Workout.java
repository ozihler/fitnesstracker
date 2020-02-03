package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.GID;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.ZonedDateTime;
import java.util.HashSet;

public class Workout {
    private final GID gid;
    private final ZonedDateTime creationDateTime;
    private WorkoutTitle workoutTitle;
    private MuscleGroups muscleGroups;

    private Workout(WorkoutTitle workoutTitle) {
        this(GID.random(), ZonedDateTime.now(), workoutTitle, new MuscleGroups(new HashSet<>()));
    }

    public static Workout newWorkout(WorkoutTitle workoutTitle) {
        return new Workout(workoutTitle);
    }

    public static Workout update(GID gid, ZonedDateTime creationDateTime, WorkoutTitle workoutTitle, MuscleGroups muscleGroups) {
        return new Workout(gid, creationDateTime, workoutTitle, muscleGroups);
    }

    private Workout(GID gid, ZonedDateTime creationDateTime, WorkoutTitle workoutTitle, MuscleGroups muscleGroups) {
        this.gid = gid;
        this.creationDateTime = creationDateTime;
        this.workoutTitle = workoutTitle;
        this.muscleGroups = muscleGroups;
    }

    public GID getGid() {
        return gid;
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
                "gid=" + gid +
                ", creationDateTime=" + creationDateTime +
                ", workoutTitle=" + workoutTitle +
                ", muscleGroups=" + muscleGroups +
                '}';
    }
}
