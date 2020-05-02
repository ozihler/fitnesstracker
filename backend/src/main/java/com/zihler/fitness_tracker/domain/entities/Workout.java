package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.LocalDate;

public class Workout {
    private final WorkoutId workoutId;
    private CreationDate creationDate;
    private WorkoutTitle workoutTitle;
    private MuscleGroups muscleGroups;

    public static Workout from(WorkoutId workoutId, WorkoutTitle workoutTitle) {
        return from(workoutId, workoutTitle, new MuscleGroups());
    }

    public static Workout from(WorkoutId workoutId, WorkoutTitle workoutTitle, MuscleGroups muscleGroups) {
        return from(workoutId, CreationDate.from(LocalDate.now()), workoutTitle, muscleGroups);
    }

    public static Workout from(WorkoutId workoutId, CreationDate creationDate, WorkoutTitle workoutTitle, MuscleGroups muscleGroups) {
        return new Workout(workoutId, creationDate, workoutTitle, muscleGroups);
    }

    private Workout(WorkoutId workoutId, CreationDate creationDate, WorkoutTitle workoutTitle, MuscleGroups muscleGroups) {
        this.workoutId = workoutId;
        this.creationDate = creationDate;
        this.workoutTitle = workoutTitle;
        this.muscleGroups = muscleGroups;
    }

    public WorkoutId getWorkoutId() {
        return workoutId;
    }

    public CreationDate getCreationDate() {
        return creationDate;
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
                "workoutId=" + workoutId +
                ", creationDate=" + creationDate +
                ", workoutTitle=" + workoutTitle +
                ", muscleGroups=" + muscleGroups +
                '}';
    }

}
