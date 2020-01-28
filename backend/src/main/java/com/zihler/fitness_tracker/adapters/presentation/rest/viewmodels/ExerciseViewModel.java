package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExerciseViewModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("sets")
    private List<SetViewModel> sets;

    public ExerciseViewModel(String name, List<SetViewModel> sets) {
        this.name = name;
        this.sets = sets;
    }

    public ExerciseViewModel() {
    }

    public String getName() {
        return name;
    }

    public List<SetViewModel> getSets() {
        return sets;
    }
}
