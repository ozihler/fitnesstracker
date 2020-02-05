package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MuscleGroupFullViewModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("exercises")
    private List<ExerciseFullViewModel> exercises;

    public MuscleGroupFullViewModel() {

    }

    public MuscleGroupFullViewModel(String name, List<ExerciseFullViewModel> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public List<ExerciseFullViewModel> getExercises() {
        return exercises;
    }
}
