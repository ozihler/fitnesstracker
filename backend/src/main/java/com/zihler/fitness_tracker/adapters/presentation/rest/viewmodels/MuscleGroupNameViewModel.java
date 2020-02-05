package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuscleGroupNameViewModel {
    @JsonProperty("name")
    private String name;

    public MuscleGroupNameViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
