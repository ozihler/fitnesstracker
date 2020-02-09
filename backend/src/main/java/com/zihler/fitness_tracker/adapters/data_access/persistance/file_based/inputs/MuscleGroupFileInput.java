package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.MuscleGroupFile;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;

public class MuscleGroupFileInput {
    private MuscleGroupFile muscleGroupFile;

    MuscleGroupFileInput(MuscleGroupFile muscleGroupFile) {
        this.muscleGroupFile = muscleGroupFile;
    }

    public MuscleGroup muscleGroup() {
        String[] exerciseNames = muscleGroupFile.getExercises().toArray(String[]::new);
        String name = muscleGroupFile.getName();
        return MuscleGroup.muscleGroup(name, exerciseNames);
    }
}
