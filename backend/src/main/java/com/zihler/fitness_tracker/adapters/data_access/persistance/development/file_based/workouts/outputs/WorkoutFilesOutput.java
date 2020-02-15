package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.musclegroups.MuscleGroupFile;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts.WorkoutFile;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

public class WorkoutFilesOutput {
    public Workout toStore;

    private WorkoutFilesOutput(Workout toStore) {
        this.toStore = toStore;
    }

    public static WorkoutFilesOutput of(Workout toStore) {
        return new WorkoutFilesOutput(toStore);
    }

    public WorkoutFile file() {
        return WorkoutFile.of(
                this.toStore.getWorkoutId().toString(),
                this.toStore.getWorkoutTitle().toString(),
                this.toStore.getCreationDateTime().toInstant().toEpochMilli(),
                toMuscleGroupFiles(this.toStore)
        );
    }

    private List<MuscleGroupFile> toMuscleGroupFiles(Workout workout) {
        return workout.getMuscleGroups().getMuscleGroups().stream()
                .map(m -> MuscleGroupFile.of(asString(m), asStrings(m.getExercises()))).collect(toUnmodifiableList());
    }

    private String asString(MuscleGroup muscleGroup) {
        return muscleGroup.getName().toString();
    }

    private List<String> asStrings(Exercises exercises) {
        return exercises.getExercises().stream().map(e -> e.getName().toString()).collect(toList());
    }
}
