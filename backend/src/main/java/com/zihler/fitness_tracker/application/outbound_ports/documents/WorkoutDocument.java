package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.GID;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.ZonedDateTime;

public class WorkoutDocument {
    private final GID id;
    private final ZonedDateTime creationDateTime;
    private final WorkoutTitle workoutTitle;

    public WorkoutDocument(GID id, ZonedDateTime creationDateTime, WorkoutTitle workoutTitle) {
        this.id = id;
        this.creationDateTime = creationDateTime;
        this.workoutTitle = workoutTitle;
    }

    public static WorkoutDocument of(Workout workout) {
        return new WorkoutDocument(workout.getId(), workout.getCreationDateTime(), workout.getWorkoutTitle());
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
