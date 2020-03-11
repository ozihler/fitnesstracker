package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.musclegroups.MuscleGroupFile;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.Objects;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupFilesInput {
    private List<MuscleGroupFile> files;

    public MuscleGroupFilesInput(List<MuscleGroupFile> files) {
        this.files = files;
    }

    public MuscleGroups toMuscleGroups() {
        return MuscleGroups.of(files
                .stream()
                .filter(Objects::nonNull)
                .map(MuscleGroupFileInput::new)
                .map(MuscleGroupFileInput::muscleGroup)
                .collect(toList()));
    }
}

