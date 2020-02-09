package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions.LoadingMuscleGroupsAndExercisesFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs.MuscleGroupFilesInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs.MuscleGroupRawFilesInput;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MuscleGroupAndExercisesFileSystemFolder {

    private Path folder;
    private String folderName;

    public MuscleGroupAndExercisesFileSystemFolder() {
        folderName = "muscleGroupsAndExercises";
        makeSourcePath();
        createFolderIfNecessary();
    }

    public MuscleGroups readMuscleGroupsAndExercises() {
        var input = fetchAllFiles();

        return input.muscleGroups();
    }

    private MuscleGroupFilesInput fetchAllFiles() {
        List<File> muscleGroupFiles = readFilesFromFolder();

        var rawInput = new MuscleGroupRawFilesInput(muscleGroupFiles);

        return rawInput.muscleGroupFiles();
    }

    private List<File> readFilesFromFolder() {
        try (Stream<Path> walk = Files.walk(folder.toAbsolutePath())) {

            return walk.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(toList());

        } catch (IOException e) {
            throw new LoadingMuscleGroupsAndExercisesFromFileSystemFailed(e.getCause());
        }
    }

    public MuscleGroups store(MuscleGroups toStore) {
        for (MuscleGroup muscleGroup : toStore.getMuscleGroups()) {
            String pathname = toFilePath(muscleGroup);
            MuscleGroupFile muscleGroupFile = MuscleGroupFile.of(muscleGroup.getName().toString(), muscleGroup.getExercises().getExercises().stream().map(e -> e.getName().toString()).collect(toList()));
            write(pathname, muscleGroupFile);
        }

        return toStore;
    }

    private void write(String pathname, MuscleGroupFile muscleGroupFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            objectMapper.writeValue(new File(pathname), muscleGroupFile);
        } catch (IOException e) {
            throw new LoadingMuscleGroupsAndExercisesFromFileSystemFailed(e.getCause());
        }
    }

    private void createFolderIfNecessary() {
        if (!Files.exists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                throw new LoadingMuscleGroupsAndExercisesFromFileSystemFailed(e.getCause());
            }
        }
    }

    private void makeSourcePath() {
        Path source = Paths.get(getClass().getResource("/").getPath());
        folder = Paths.get(source.toAbsolutePath() + "/" + folderName);
    }

    private String toFilePath(MuscleGroup muscleGroup) {
        String muscleGroupName = muscleGroup.getName().toString().toLowerCase();
        return folder.toAbsolutePath() + "/" + muscleGroupName + ".json";
    }
}
