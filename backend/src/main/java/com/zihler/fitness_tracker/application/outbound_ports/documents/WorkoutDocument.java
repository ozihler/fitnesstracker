package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.ZonedDateTime;

public class WorkoutDocument {
    private final WorkoutId workoutId;
    private final ZonedDateTime creationDateTime;
    private final WorkoutTitle workoutTitle;
    private MuscleGroupsDocument muscleGroups;

    public WorkoutDocument(WorkoutId workoutId, ZonedDateTime creationDateTime, WorkoutTitle workoutTitle, MuscleGroupsDocument muscleGroups) {
        this.workoutId = workoutId;
        this.creationDateTime = creationDateTime;
        this.workoutTitle = workoutTitle;
        this.muscleGroups = muscleGroups;
    }

    public static WorkoutDocument of(Workout workout) {
        return new WorkoutDocument(workout.getWorkoutId(),
                workout.getCreationDateTime(),
                workout.getWorkoutTitle(),
                MuscleGroupsDocument.of(workout.getMuscleGroups()));
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

    public MuscleGroupsDocument getMuscleGroups() {
        return muscleGroups;
    }
}
