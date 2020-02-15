package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.musclegroups.MuscleGroupFile;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts.WorkoutFile;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkoutFileInput {
    private WorkoutFile workoutFile;

    WorkoutFileInput(WorkoutFile workoutFile) {
        this.workoutFile = workoutFile;
    }

    public Workout workout() {
        return new Workout(
                WorkoutId.of(workoutFile.getWorkoutId()),
                ZonedDateTime.from(Instant.ofEpochMilli(workoutFile.getCreationDate())),
                WorkoutTitle.of(workoutFile.getTitle()),
                toMuscleGroups(workoutFile)
        );
    }

    private MuscleGroups toMuscleGroups(WorkoutFile w) {
        Set<MuscleGroup> muscleGroups = w.getMuscleGroups()
                .stream()
                .map(this::toMuscleGroup)
                .collect(Collectors.toSet());

        return MuscleGroups.of(muscleGroups);
    }

    private MuscleGroup toMuscleGroup(MuscleGroupFile m) {
        return MuscleGroup.of(m.getName(), m.getExercises().toArray(String[]::new));
    }
}
