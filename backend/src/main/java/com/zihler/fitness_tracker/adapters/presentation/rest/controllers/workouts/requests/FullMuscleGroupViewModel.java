package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FullMuscleGroupViewModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("exercises")
    private List<FullExerciseViewModel> exercises;

    public FullMuscleGroupViewModel() {

    }

    public FullMuscleGroupViewModel(String name, List<FullExerciseViewModel> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public List<FullExerciseViewModel> getExercises() {
        return exercises;
    }
}
