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
    @JsonProperty("isSelectable")
    private boolean selectable;

    public ExerciseViewModel(String name, List<SetViewModel> sets, int multiplier, boolean selectable) {
        this.name = name;
        this.sets = sets;
        this.multiplier = multiplier;
        this.selectable = selectable;
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

    public boolean getSelectable() {
        return selectable;
    }

    @Override
    public String toString() {
        return "ExerciseViewModel{" +
                "name='" + name + '\'' +
                ", sets=" + sets +
                ", multiplier=" + multiplier +
                ", isSelectable=" + selectable +
                '}';
    }
}
