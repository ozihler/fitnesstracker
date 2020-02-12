package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.MuscleGroupFile;

import java.io.File;
import java.nio.file.Path;

public class MuscleGroupRawFileOutput {
    private Path folder;
    private MuscleGroupFile muscleGroup;

    public MuscleGroupRawFileOutput(Path folder, MuscleGroupFile muscleGroup) {
        this.folder = folder;
        this.muscleGroup = muscleGroup;
    }

    public File file() {
        return new File(this.folder.toAbsolutePath() + "/" + muscleGroup.fileName());
    }

    public MuscleGroupFile muscleGroup() {
        return muscleGroup;
    }
}
