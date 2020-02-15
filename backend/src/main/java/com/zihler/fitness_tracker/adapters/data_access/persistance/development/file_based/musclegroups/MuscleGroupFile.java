package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.musclegroups;

import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.JsonReadWritableFile;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.join;

public class MuscleGroupFile implements JsonReadWritableFile {
    private String name;
    private List<String> exercises;

    public MuscleGroupFile() {
    }

    private MuscleGroupFile(String name, List<String> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public static MuscleGroupFile of(String name, List<String> exercises) {
        return new MuscleGroupFile(name, exercises);
    }

    public List<String> getExercises() {
        return exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String fileName() {
        return name.replace(" ", "_") + ".json";
    }

    @Override
    public String toString() {
        return "MuscleGroupFile{" +
                "name='" + name + '\'' +
                ", exercises=" + join(exercises, ',') +
                '}';
    }
}
