package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

public class WorkoutDocument {
    private final WorkoutId workoutId;
    private final WorkoutTitle workoutTitle;
    private final CreationDate creationDate;
    private MuscleGroupsDocument muscleGroups;
    private boolean isDeleted;

    public WorkoutDocument(WorkoutId workoutId, CreationDate creationDate, WorkoutTitle workoutTitle, MuscleGroupsDocument muscleGroups, boolean isDeleted) {
        this.workoutId = workoutId;
        this.creationDate = creationDate;
        this.workoutTitle = workoutTitle;
        this.muscleGroups = muscleGroups;
        this.isDeleted = isDeleted;
    }

    //todo this should not be here --> into a Role
    public static WorkoutDocument of(Workout workout) {
        return new WorkoutDocument(workout.getWorkoutId(),
                workout.getCreationDate(),
                workout.getWorkoutTitle(),
                MuscleGroupsDocument.of(workout.getMuscleGroups()), workout.isDeleted());
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

    public MuscleGroupsDocument getMuscleGroups() {
        return muscleGroups;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }
}
