package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MuscleGroupViewModel {
    @JsonProperty("name")
    private String name;

    public MuscleGroupViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
