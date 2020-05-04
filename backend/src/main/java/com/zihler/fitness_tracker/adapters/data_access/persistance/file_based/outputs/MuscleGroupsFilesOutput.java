package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.List;
import java.util.stream.Collectors;

public class MuscleGroupsFilesOutput {
    private MuscleGroups muscleGroups;

    public MuscleGroupsFilesOutput(MuscleGroups muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public List<MuscleGroupViewModel> files() {
        return muscleGroups.getMuscleGroups().stream()
                .map(m -> new MuscleGroupViewModel(
                        m.getName().toString(),
                        new ExercisesFilesOutput(m.getExercises()).files()))
                .collect(Collectors.toList());
    }
}
