package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions.LoadingMuscleGroupsAndExercisesFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.MuscleGroupFile;

import java.io.File;
import java.io.IOException;

public class MuscleGroupRawFileInput {
    private File muscleGroupRawFile;

    public MuscleGroupRawFileInput(File muscleGroupRawFile) {
        this.muscleGroupRawFile = muscleGroupRawFile;
    }

    public MuscleGroupFile muscleGroupFile() {
        try {
            return new ObjectMapper().readValue(muscleGroupRawFile, MuscleGroupFile.class);
        } catch (IOException e) {
            throw new LoadingMuscleGroupsAndExercisesFromFileSystemFailed(e.getCause());
        }
    }
}
