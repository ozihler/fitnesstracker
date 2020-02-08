package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExerciseViewModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("sets")
    private List<FullSetViewModel> sets;

    public ExerciseViewModel(String name, List<FullSetViewModel> sets) {
        this.name = name;
        this.sets = sets;
    }

    public ExerciseViewModel() {
    }

    public String getName() {
        return name;
    }

    public List<FullSetViewModel> getSets() {
        return sets;
    }


    @Override
    public String toString() {
        return String.format("ExerciseViewModel{name='%s', sets=%s}", name, sets);
    }


}
