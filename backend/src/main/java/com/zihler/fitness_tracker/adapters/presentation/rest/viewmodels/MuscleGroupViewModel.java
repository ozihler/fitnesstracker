package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class MuscleGroupViewModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("exercises")
    private List<ExerciseViewModel> exercises;

    public MuscleGroupViewModel() {

    }

    public MuscleGroupViewModel(String name) {
        this(name, new ArrayList<>());
    }

    public MuscleGroupViewModel(String name, List<ExerciseViewModel> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public List<ExerciseViewModel> getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        return "FullMuscleGroupViewModel{" +
                "name='" + name + '\'' +
                ", exercises=" +  exercises.stream().map(ExerciseViewModel::toString).collect(joining(",")) +
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
}
