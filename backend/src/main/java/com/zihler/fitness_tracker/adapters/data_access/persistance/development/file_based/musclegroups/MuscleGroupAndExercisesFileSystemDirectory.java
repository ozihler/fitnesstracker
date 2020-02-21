package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.musclegroups;

import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.FileSystemDirectory;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.musclegroups.inputs.MuscleGroupFilesInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.musclegroups.outputs.MuscleGroupFilesOutput;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

public class MuscleGroupAndExercisesFileSystemDirectory {

    private FileSystemDirectory<MuscleGroupFile> fileSystemDirectory;

    public static MuscleGroupAndExercisesFileSystemDirectory mkDir(String muscleGroupsAndExercisesDirectory) {
        return new MuscleGroupAndExercisesFileSystemDirectory(muscleGroupsAndExercisesDirectory);
    }

    private MuscleGroupAndExercisesFileSystemDirectory(String folderName) {
        this.fileSystemDirectory = FileSystemDirectory.mkDir(folderName, MuscleGroupFile.class);
    }

    public MuscleGroups fetch() {
        return new MuscleGroupFilesInput(fileSystemDirectory.fetchAllFilesInDirectory())
                .toMuscleGroups();
    }

    public MuscleGroups save(MuscleGroups muscleGroupsToStore) {

        MuscleGroupFilesOutput.of(muscleGroupsToStore)
                .files()
                .forEach(this::writeToFileSystem);

        return muscleGroupsToStore;
    }

    private void writeToFileSystem(MuscleGroupFile output) {
        this.fileSystemDirectory.store(output);
    }

    public void remove() {
        fileSystemDirectory.remove();
    }
}
