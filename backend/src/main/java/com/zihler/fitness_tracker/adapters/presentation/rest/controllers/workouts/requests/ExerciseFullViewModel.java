package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;

import java.util.List;

public class ExerciseFullViewModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("sets")
    private List<SetViewModel> sets;

    public ExerciseFullViewModel(String name, List<SetViewModel> sets) {
        this.name = name;
        this.sets = sets;
    }

    public ExerciseFullViewModel() {
    }

    public String getName() {
        return name;
    }

    public List<SetViewModel> getSets() {
        return sets;
    }
}
