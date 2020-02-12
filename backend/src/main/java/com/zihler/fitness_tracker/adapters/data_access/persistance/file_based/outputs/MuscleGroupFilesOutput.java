package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.MuscleGroupFile;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupFilesOutput {
    public MuscleGroups toStore;

    private MuscleGroupFilesOutput(MuscleGroups toStore) {
        this.toStore = toStore;
    }

    public static MuscleGroupFilesOutput of(MuscleGroups toStore) {
        return new MuscleGroupFilesOutput(toStore);
    }

    public List<MuscleGroupFile> files() {
        return toStore.getMuscleGroups()
                    .stream()
                    .map(muscleGroup ->
                            MuscleGroupFile.of(
                                    muscleGroup.getName().toString(),
                                    muscleGroup.getExercises().getExercises().stream().map(e -> e.getName().toString()).collect(toList())))
                    .collect(toList());
    }
}
