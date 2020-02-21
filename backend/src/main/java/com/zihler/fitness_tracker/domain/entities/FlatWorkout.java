package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.ExerciseName;
import com.zihler.fitness_tracker.domain.values.MuscleGroupName;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.ZonedDateTime;
import java.util.List;

public class FlatWorkout {
    private WorkoutId workoutId;
    private ZonedDateTime creationDateTime;
    private WorkoutTitle workoutTitle;
    private FlatSets sets = new FlatSets();

    FlatWorkout(WorkoutId workoutId, ZonedDateTime creationDateTime, WorkoutTitle workoutTitle) {
        this.workoutId = workoutId;
        this.creationDateTime = creationDateTime;
        this.workoutTitle = workoutTitle;
    }

    public void add(FlatSet set) {
        sets.add(set);
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

    public FlatSets getSets() {
        return sets;
    }

    List<MuscleGroupName> getMuscleGroupNames() {
        return sets.getMuscleGroupNames();
    }

    List<ExerciseName> getExerciseNames() {
        return sets.getExerciseNames();
    }
}
