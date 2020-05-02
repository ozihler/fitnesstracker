package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.FileSystemDirectory;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.inputs.WorkoutFilesInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.outputs.WorkoutFilesOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Workouts;

public class WorkoutFileSystemDirectory {

    private FileSystemDirectory<WorkoutViewModel> fileSystemDirectory;

    private WorkoutFileSystemDirectory(String folderName) {
        this.fileSystemDirectory = FileSystemDirectory.mkDir(folderName, WorkoutViewModel.class);
    }

    public static WorkoutFileSystemDirectory mkDir(String workoutsFolderPath) {
        return new WorkoutFileSystemDirectory(workoutsFolderPath);
    }

    public Workouts fetch() {
        return new WorkoutFilesInput(fileSystemDirectory.fetchAllFilesInDirectory())
                .workouts();
    }

    public Workout save(Workout workout) {
        WorkoutViewModel file = WorkoutFilesOutput.from(workout).asFile();
        this.fileSystemDirectory.store(file);
        return workout;
    }

    public void remove() {
        this.fileSystemDirectory.remove();
    }
}
