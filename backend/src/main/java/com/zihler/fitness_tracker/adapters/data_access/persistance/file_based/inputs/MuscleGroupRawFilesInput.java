package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs;

import java.io.File;
import java.util.List;

import static java.util.stream.Collectors.toSet;

public class MuscleGroupRawFilesInput {
    private List<File> muscleGroupFiles;

    public MuscleGroupRawFilesInput(List<File> muscleGroupFiles) {
        this.muscleGroupFiles = muscleGroupFiles;
    }

    public MuscleGroupFilesInput toMuscleGroupFiles() {
        return new MuscleGroupFilesInput(muscleGroupFiles.stream()
                .map(MuscleGroupRawFileInput::new)
                .map(MuscleGroupRawFileInput::muscleGroupFile)
                .collect(toSet()));
    }
}
