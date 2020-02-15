package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.JsonReadWritableFile;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.MuscleGroupFile;

public class MuscleGroupRawFileOutput implements FileOutput {
    private MuscleGroupFile muscleGroup;

    public MuscleGroupRawFileOutput(MuscleGroupFile muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    @Override
    public JsonReadWritableFile data() {
        return muscleGroup;
    }
}
