package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Name;

import java.util.List;
import java.util.stream.Collectors;

public class MuscleGroupsFilesInput {
    private List<MuscleGroupViewModel> viewModels;

    public MuscleGroupsFilesInput(List<MuscleGroupViewModel> viewModels) {
        this.viewModels = viewModels;
    }

    public MuscleGroups muscleGroups() {

        return MuscleGroups.of(
                viewModels.stream()
                        .map(MuscleGroupsFilesInput::muscleGroup)
                        .collect(Collectors.toList())
        );
    }

    private static MuscleGroup muscleGroup(MuscleGroupViewModel viewModel) {
        ExercisesFilesInput exercises = new ExercisesFilesInput(viewModel.getExercises());
        return new MuscleGroup(Name.of(viewModel.getName()), exercises.exercises());
    }
}
