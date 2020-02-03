package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class ExercisesViewModel {
    @JsonProperty("exercises")
    private Set<ExerciseViewModel> exercises;

    public ExercisesViewModel() {
    }

    public ExercisesViewModel(Set<ExerciseViewModel> exercises) {
        this.exercises = exercises;
    }

    public Set<ExerciseViewModel> getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        return String.format("ExercisesViewModel{%s}", exercises);
    }
}
