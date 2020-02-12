package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions.LoadingMuscleGroupsAndExercisesFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions.StoringMuscleGroupsAndExercisesToFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs.MuscleGroupRawFilesInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs.MuscleGroupFilesOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs.MuscleGroupRawFileOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs.MuscleGroupsRawFilesOutput;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupAndExercisesFileSystemDirectory {

    private Path folder;
    private String folderName;
    private ObjectMapper jsonFileWriter;

    private MuscleGroupAndExercisesFileSystemDirectory() {
        folderName = "muscleGroupsAndExercises";
        makeSourcePath();
        createFolderIfNecessary();
        configureFileWriter();
    }

    public static MuscleGroupAndExercisesFileSystemDirectory makeDirectory() {
        return new MuscleGroupAndExercisesFileSystemDirectory();
    }

    private void configureFileWriter() {
        jsonFileWriter = new ObjectMapper();
        jsonFileWriter.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public MuscleGroups fetch() {
        return fetchFromFileSystem()
                .toMuscleGroupFiles()
                .toMuscleGroups();
    }

    public MuscleGroupRawFilesInput fetchFromFileSystem() {
        List<File> muscleGroupFiles = fetchFilesFromFileSystem();

        return new MuscleGroupRawFilesInput(muscleGroupFiles);
    }

    private List<File> fetchFilesFromFileSystem() {
        try {
            return Files.walk(folder.toAbsolutePath()).filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(toList());
        } catch (IOException e) {
            throw new LoadingMuscleGroupsAndExercisesFromFileSystemFailed(e.getCause());
        }
    }

    public MuscleGroups save(MuscleGroups muscleGroupsToStore) {

        List<MuscleGroupFile> files = MuscleGroupFilesOutput
                .of(muscleGroupsToStore)
                .files();

        new MuscleGroupsRawFilesOutput(folder, files)
                .rawFiles()
                .forEach(this::writeToFileSystem);

        return muscleGroupsToStore;
    }

    private void writeToFileSystem(MuscleGroupRawFileOutput output) {
        try {
            jsonFileWriter.writeValue(output.file(), output.muscleGroup());
        } catch (IOException e) {
            throw new StoringMuscleGroupsAndExercisesToFileSystemFailed(e.getCause());
        }
    }

    private void createFolderIfNecessary() {
        if (Files.exists(folder)) {
            return;
        }

        try {
            Files.createDirectories(folder);
        } catch (IOException e) {
            throw new LoadingMuscleGroupsAndExercisesFromFileSystemFailed(e.getCause());
        }
    }

    private void makeSourcePath() {
        String path = getClass().getResource("/").getPath().replace("/C:","C:");
        System.err.println(path);
        Path source = Paths.get(path);
        folder = Paths.get(source.toAbsolutePath() + "/" + folderName);
    }

}
