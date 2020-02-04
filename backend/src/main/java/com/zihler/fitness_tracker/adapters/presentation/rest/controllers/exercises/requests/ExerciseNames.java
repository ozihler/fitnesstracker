package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExerciseNames {

    @JsonProperty("input")
    private String input;

    public ExerciseNames() {
    }

    public String getInput() {
        return this.input;
    }

    @Override
    public String toString() {
        return String.format("ExerciseNames{'%s'}", input);
    }
}
