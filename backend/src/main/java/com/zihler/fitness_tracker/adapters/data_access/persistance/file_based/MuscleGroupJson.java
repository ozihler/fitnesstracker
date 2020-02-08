package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based;

import java.util.List;

class MuscleGroupJson {
    private String name;
    private List<String> exercises;

    public MuscleGroupJson() {
    }

    private MuscleGroupJson(String name, List<String> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public static MuscleGroupJson of(String name, List<String> exercises) {
        return new MuscleGroupJson(name, exercises);
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
}
