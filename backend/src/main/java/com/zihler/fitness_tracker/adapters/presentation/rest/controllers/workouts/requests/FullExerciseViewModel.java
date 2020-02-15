package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullSetViewModel;
import org.apache.logging.log4j.util.Strings;

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

    @Override
    public String toString() {
        return "FullExerciseViewModel{" +
                "name='" + name + '\'' +
                ", sets=" + Strings.join(sets, ',') +
                '}';
    }
}
