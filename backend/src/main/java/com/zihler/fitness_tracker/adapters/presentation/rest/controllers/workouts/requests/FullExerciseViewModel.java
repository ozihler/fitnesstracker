package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullSetViewModel;

import java.util.List;

public class FullExerciseViewModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("sets")
    private List<FullSetViewModel> sets;

    public FullExerciseViewModel(String name, List<FullSetViewModel> sets) {
        this.name = name;
        this.sets = sets;
    }

    public FullExerciseViewModel() {
    }

    public String getName() {
        return name;
    }

    public List<FullSetViewModel> getSets() {
        return sets;
    }
}
