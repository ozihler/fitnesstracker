package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MuscleGroupJson {
    @JsonProperty("name")
    private String name;
    @JsonProperty("exercises")
    private List<ExerciseJson> exercises;
    @JsonProperty("isSelectable")
    private boolean isSelectable;

    public MuscleGroupJson() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseJson> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseJson> exercises) {
        this.exercises = exercises;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }

    @Override
    public String toString() {
        return "MuscleGroupJson{" +
                "name='" + name + '\'' +
                ", exercises=" + exercises +
                ", isSelectable=" + isSelectable +
                '}';
    }
}
