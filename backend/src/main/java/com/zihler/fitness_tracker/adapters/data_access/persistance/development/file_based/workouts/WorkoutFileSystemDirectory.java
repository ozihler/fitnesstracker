package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts;

import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.FileSystemDirectory;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts.inputs.WorkoutFilesInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts.outputs.WorkoutFilesOutput;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Workouts;

public class WorkoutFileSystemDirectory {

    private FileSystemDirectory<WorkoutFile> fileSystemDirectory;

    private WorkoutFileSystemDirectory(String folderName) {
        this.fileSystemDirectory = FileSystemDirectory.mkDir(folderName, WorkoutFile.class);
    }

    public static WorkoutFileSystemDirectory makeDirectory() {
        return new WorkoutFileSystemDirectory("workouts");
    }

    public Workouts fetch() {
        return new WorkoutFilesInput(fileSystemDirectory.fetchAllFilesInDirectory())
                .workouts();
    }

    public Workout save(Workout workout) {
        WorkoutFile file = WorkoutFilesOutput.of(workout).file();
        this.fileSystemDirectory.store(file);
        return workout;
    }
}
