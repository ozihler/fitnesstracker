package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions.LoadingMuscleGroupsAndExercisesFromFileSystemFailed;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class FileSystem {

    private Path folder;

    public FileSystem() {
        makeSourcePath();
        createFolderIfNecessary();
    }

    public MuscleGroups readMuscleGroupsAndExercises() {
        List<File> muscleGroupFiles = loadFromFile();

        return MuscleGroups.of(muscleGroupFiles.stream()
                .map(this::readMuscleGroup)
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(toSet()));
    }

    private MuscleGroup toEntity(MuscleGroupJson muscleGroupJson) {
        String[] exerciseNames = muscleGroupJson.getExercises().toArray(String[]::new);
        String name = muscleGroupJson.getName();
        return MuscleGroup.muscleGroup(name, exerciseNames);
    }

    private MuscleGroupJson readMuscleGroup(File file) {
        try {
            return new ObjectMapper().readValue(file, MuscleGroupJson.class);
        } catch (IOException e) {
            throwException(e);
            return null;
        }
    }

    private void throwException(IOException e) {
        throw new LoadingMuscleGroupsAndExercisesFromFileSystemFailed(e.getCause());
    }

    private List<File> loadFromFile() {
        List<File> files = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(folder.toAbsolutePath())) {
            files = walk.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(toList());
        } catch (IOException e) {
            throwException(e);
        }
        return files;
    }

    public MuscleGroups store(MuscleGroups toStore) {
        for (MuscleGroup muscleGroup : toStore.getMuscleGroups()) {
            String pathname = toFilePath(muscleGroup);
            MuscleGroupJson muscleGroupJson = MuscleGroupJson.of(muscleGroup.getName().toString(), muscleGroup.getExercises().getExercises().stream().map(e -> e.getName().toString()).collect(toList()));
            write(pathname, muscleGroupJson);
        }

        return toStore;
    }

    private void write(String pathname, MuscleGroupJson muscleGroupJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            objectMapper.writeValue(new File(pathname), muscleGroupJson);
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
        folder = Paths.get(source.toAbsolutePath() + "/muscleGroupsAndExercises");
    }

    private String toFilePath(MuscleGroup muscleGroup) {
        String muscleGroupName = muscleGroup.getName().toString().toLowerCase();
        return folder.toAbsolutePath() + "/" + muscleGroupName + ".json";
    }
}
