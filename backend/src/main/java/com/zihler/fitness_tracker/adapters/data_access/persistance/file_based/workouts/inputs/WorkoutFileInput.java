package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;

public class WorkoutFileInput {
    private final WorkoutViewModel workoutFile;

    WorkoutFileInput(WorkoutViewModel workoutFile) {
        this.workoutFile = workoutFile;
    }

    public Workout workout() {
        return new Workout(WorkoutId.of(workoutFile.getWorkoutId()), CreationDate.of(LocalDate.from(Instant.ofEpochMilli(workoutFile.getCreationDate()).atZone(Clock.systemDefaultZone().getZone()))), WorkoutTitle.of(workoutFile.getTitle()), new MuscleGroupsFilesInput(workoutFile.getMuscleGroups()).muscleGroups());
    }

}
