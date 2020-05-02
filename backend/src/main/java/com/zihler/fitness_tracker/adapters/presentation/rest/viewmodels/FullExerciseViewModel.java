package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullSetViewModel;

import java.util.List;

import static java.util.stream.Collectors.joining;

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

    @Override
    public String toString() {
        return "FullExerciseViewModel{" +
                "name='" + name + '\'' +
                ", sets=" + sets.stream().map(FullSetViewModel::toString).collect(joining(",")) +
                '}';
    }
}
