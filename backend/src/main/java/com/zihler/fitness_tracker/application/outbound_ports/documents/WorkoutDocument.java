package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.LocalDate;

public class WorkoutDocument {
    private final WorkoutId workoutId;
    private final WorkoutTitle workoutTitle;
    private final CreationDate creationDate;
    private MuscleGroupsDocument muscleGroups;

    public WorkoutDocument(WorkoutId workoutId, CreationDate creationDate, WorkoutTitle workoutTitle, MuscleGroupsDocument muscleGroups) {
        this.workoutId = workoutId;
        this.creationDate = creationDate;
        this.workoutTitle = workoutTitle;
        this.muscleGroups = muscleGroups;
    }

    public static WorkoutDocument of(Workout workout) {
        return new WorkoutDocument(workout.getWorkoutId(),
                workout.getCreationDate(),
                workout.getWorkoutTitle(),
                MuscleGroupsDocument.of(workout.getMuscleGroups()));
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
}
