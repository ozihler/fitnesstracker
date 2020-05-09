package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ExerciseViewModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("sets")
    private List<SetViewModel> sets;
    @JsonProperty("multiplier")
    private int multiplier;
    private boolean isSelectable;

    public ExerciseViewModel(String name, List<SetViewModel> sets, int multiplier, boolean isSelectable) {
        this.name = name;
        this.sets = sets;
        this.multiplier = multiplier;
        this.isSelectable = isSelectable;
    }


    public ExerciseViewModel() {
    }

    public ExerciseViewModel(String name) {
        this(name, new ArrayList<>(), 1, true);
    }

    public String getName() {
        return name;
    }

    public List<SetViewModel> getSets() {
        return sets;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public boolean getIsSelectable() {
        return isSelectable;
    }

    @Override
    public String toString() {
        return "ExerciseViewModel{" +
                "name='" + name + '\'' +
                ", sets=" + sets +
                ", multiplier=" + multiplier +
                ", isSelectable=" + isSelectable +
                '}';
    }
}
