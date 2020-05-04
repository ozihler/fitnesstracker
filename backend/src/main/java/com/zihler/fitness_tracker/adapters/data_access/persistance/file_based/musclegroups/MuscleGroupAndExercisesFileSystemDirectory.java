package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.musclegroups;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.FileSystemDirectory;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs.MuscleGroupsFilesOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.inputs.MuscleGroupsFilesInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupAndExercisesFileSystemDirectory {

    private FileSystemDirectory<MuscleGroupViewModel> fileSystemDirectory;

    public static MuscleGroupAndExercisesFileSystemDirectory mkDir(String muscleGroupsAndExercisesDirectory) {
        return new MuscleGroupAndExercisesFileSystemDirectory(muscleGroupsAndExercisesDirectory);
    }

    private MuscleGroupAndExercisesFileSystemDirectory(String folderName) {
        this.fileSystemDirectory = FileSystemDirectory.mkDir(folderName, MuscleGroupViewModel.class);
    }

    public MuscleGroups fetch() {
        List<MuscleGroupViewModel> muscleGroupViewModels = fileSystemDirectory.fetchAllFilesInDirectory();

        return new MuscleGroupsFilesInput(muscleGroupViewModels).muscleGroups();

    }

    public MuscleGroups save(MuscleGroups muscleGroupsToStore) {
        new MuscleGroupsFilesOutput(muscleGroupsToStore)
                .files()
                .forEach(this::writeToFileSystem);

        return muscleGroupsToStore;
    }

    private void writeToFileSystem(MuscleGroupViewModel output) {
        this.fileSystemDirectory.store(output);
    }

    public void clearFolder() {
        fileSystemDirectory.clearFolder();
    }
}
