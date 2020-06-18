package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExerciseJson {

    @JsonProperty("name")
    private String name;
    @JsonProperty("sets")
    private List<SetJson> sets;
    @JsonProperty("multiplier")
    private int multiplier;
    @JsonProperty("isSelectable")
    private boolean isSelectable;

    public ExerciseJson() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SetJson> getSets() {
        return sets;
    }

    public void setSets(List<SetJson> sets) {
        this.sets = sets;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }

    @Override
    public String toString() {
        return "ExerciseJson{" +
                "name='" + name + '\'' +
                ", sets=" + sets +
                ", multiplier=" + multiplier +
                ", isSelectable=" + isSelectable +
                '}';
    }
}
