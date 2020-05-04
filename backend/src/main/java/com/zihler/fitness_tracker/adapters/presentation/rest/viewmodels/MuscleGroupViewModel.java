package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.Namable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class MuscleGroupViewModel implements Namable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("exercises")
    private List<ExerciseViewModel> exercises;

    @JsonProperty("isSelectable")
    private boolean selectable;

    public MuscleGroupViewModel() {
    }

    public MuscleGroupViewModel(String name) {
        this(name, new ArrayList<>(), true);
    }

    public MuscleGroupViewModel(String name, List<ExerciseViewModel> exercises, boolean selectable) {
        this.name = name;
        this.exercises = exercises;
        this.selectable = selectable;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public String getName() {
        return name;
    }

    public List<ExerciseViewModel> getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        return "MuscleGroupViewModel{" +
                "name='" + name + '\'' +
                ", exercises=" + exercises +
                ", isSelectable=" + selectable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MuscleGroupViewModel that = (MuscleGroupViewModel) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String name() {
        return name;
    }
}
