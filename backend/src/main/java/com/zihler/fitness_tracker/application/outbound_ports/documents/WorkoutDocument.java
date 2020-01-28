package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.GID;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.ZonedDateTime;

public class WorkoutDocument {
    private final GID gid;
    private final ZonedDateTime creationDateTime;
    private final WorkoutTitle workoutTitle;
    private MuscleGroupsDocument muscleGroups;

    public WorkoutDocument(GID gid, ZonedDateTime creationDateTime, WorkoutTitle workoutTitle, MuscleGroupsDocument muscleGroups) {
        this.gid = gid;
        this.creationDateTime = creationDateTime;
        this.workoutTitle = workoutTitle;
        this.muscleGroups = muscleGroups;
    }

    public static WorkoutDocument of(Workout workout) {
        return new WorkoutDocument(workout.getGid(),
                workout.getCreationDateTime(),
                workout.getWorkoutTitle(),
                MuscleGroupsDocument.of(workout.getMuscleGroups()));
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

    public MuscleGroupsDocument getMuscleGroups() {
        return this.muscleGroups;
    }
}
