package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs.MuscleGroupFilesInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs.MuscleGroupFilesOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs.MuscleGroupRawFileOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs.MuscleGroupsRawFilesOutput;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.List;

public class MuscleGroupAndExercisesFileSystemDirectory {

    private FileSystemDirectory<MuscleGroupFile> fileSystemDirectory;

    public static MuscleGroupAndExercisesFileSystemDirectory makeDirectory() {
        return new MuscleGroupAndExercisesFileSystemDirectory("muscleGroupsAndExercises");
    }

    private MuscleGroupAndExercisesFileSystemDirectory(String folderName) {
        this.fileSystemDirectory = FileSystemDirectory.mkDir("folderName", MuscleGroupFile.class);
    }


    public MuscleGroups fetch() {
        return new MuscleGroupFilesInput(fileSystemDirectory.fetchAllFilesFromFileSystem())
                .toMuscleGroups();
    }

    public MuscleGroups save(MuscleGroups muscleGroupsToStore) {

        List<MuscleGroupFile> files = MuscleGroupFilesOutput
                .of(muscleGroupsToStore)
                .files();

        new MuscleGroupsRawFilesOutput(files)
                .rawFiles()
                .forEach(this::writeToFileSystem);

        return muscleGroupsToStore;
    }

    private void writeToFileSystem(MuscleGroupRawFileOutput output) {
        this.fileSystemDirectory.store(output);
    }


}
