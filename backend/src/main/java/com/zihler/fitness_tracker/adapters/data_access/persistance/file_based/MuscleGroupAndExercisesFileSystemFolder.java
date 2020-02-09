package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions.LoadingMuscleGroupsAndExercisesFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs.MuscleGroupFilesInput;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class MuscleGroupAndExercisesFileSystemFolder {

    private Path folder;
    private String folderName;

    public MuscleGroupAndExercisesFileSystemFolder() {
        folderName = "muscleGroupsAndExercises";
        makeSourcePath();
        createFolderIfNecessary();
    }

    public MuscleGroups readMuscleGroupsAndExercises() {
        List<File> muscleGroupFiles = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(folder.toAbsolutePath())) {
            muscleGroupFiles = walk.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(toList());
        } catch (IOException e) {
            throwException(e);
        }

        Set<MuscleGroupFile> files = muscleGroupFiles.stream()
                .map(this::readMuscleGroup)
                .collect(toSet());

        var input = new MuscleGroupFilesInput(files);

        return input.muscleGroups();
    }

    private MuscleGroupFile readMuscleGroup(File file) {
        try {
            return new ObjectMapper().readValue(file, MuscleGroupFile.class);
        } catch (IOException e) {
            throwException(e);
            return null;
        }
    }

    private void throwException(IOException e) {
        throw new LoadingMuscleGroupsAndExercisesFromFileSystemFailed(e.getCause());
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
            throwException(e);
        }
    }

    private void createFolderIfNecessary() {
        if (!Files.exists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                throwException(e);
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
