package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.MuscleGroupFile;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MuscleGroupFilesInput {
    private Set<MuscleGroupFile> files;

    public MuscleGroupFilesInput(Set<MuscleGroupFile> files) {
        this.files = files;
    }

    public MuscleGroups toMuscleGroups() {
        return MuscleGroups.of(files
                .stream()
                .filter(Objects::nonNull)
                .map(MuscleGroupFileInput::new)
                .map(MuscleGroupFileInput::muscleGroup)
                .collect(toSet()));
    }
}

