package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.musclegroups.MuscleGroupFile;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupFilesOutput {
    public MuscleGroups toStore;

    public static MuscleGroupFilesOutput of(MuscleGroups toStore) {
        return new MuscleGroupFilesOutput(toStore);
    }

    private MuscleGroupFilesOutput(MuscleGroups toStore) {
        this.toStore = toStore;
    }

    public List<MuscleGroupFile> files() {
        return toStore.getMuscleGroups()
                .stream()
                .map(this::toMuscleGroupsFile)
                .collect(toList());
    }

    private MuscleGroupFile toMuscleGroupsFile(MuscleGroup muscleGroup) {
        return MuscleGroupFile.of(asString(muscleGroup), asStrings(muscleGroup.getExercises()));
    }

    private String asString(MuscleGroup muscleGroup) {
        return muscleGroup.getName().toString();
    }

    private List<String> asStrings(Exercises exercises) {
        return exercises.getExercises().stream().map(e -> e.getName().toString()).collect(toList());
    }
}
