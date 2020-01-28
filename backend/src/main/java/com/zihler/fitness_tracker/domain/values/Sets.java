package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Set;

import java.util.List;

public class Sets {

    private List<Set> sets;

    public Sets(List<Set> sets) {
        this.sets = sets;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void add(Set set) {
        this.sets.add(set);
    }
}
