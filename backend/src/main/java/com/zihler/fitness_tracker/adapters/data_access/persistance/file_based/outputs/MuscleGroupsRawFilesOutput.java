package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.MuscleGroupFile;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupsRawFilesOutput {
    private List<MuscleGroupFile> files;

    public MuscleGroupsRawFilesOutput(List<MuscleGroupFile> files) {
        this.files = files;
    }

    public List<MuscleGroupRawFileOutput> rawFiles() {
        return files.stream()
                .map(MuscleGroupRawFileOutput::new)
                .collect(toList());
    }
}
