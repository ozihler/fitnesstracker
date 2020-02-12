package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.MuscleGroupFile;

import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupsRawFilesOutput {
    private Path folder;
    private List<MuscleGroupFile> files;

    public MuscleGroupsRawFilesOutput(Path folder, List<MuscleGroupFile> files) {
        this.folder = folder;
        this.files = files;
    }

    public List<MuscleGroupRawFileOutput> rawFiles() {
        return files.stream()
                .map(muscleGroup -> new MuscleGroupRawFileOutput(this.folder, muscleGroup))
                .collect(toList());
    }
}
