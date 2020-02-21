package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExercisesViewModel {
    @JsonProperty("exercises")
    private List<ExerciseViewModel> exercises;

    public ExercisesViewModel() {
    }

    public ExercisesViewModel(List<ExerciseViewModel> exercises) {
        this.exercises = exercises;
    }

    public List<ExerciseViewModel> getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        return String.format("ExercisesViewModel{%s}", exercises);
    }
}
