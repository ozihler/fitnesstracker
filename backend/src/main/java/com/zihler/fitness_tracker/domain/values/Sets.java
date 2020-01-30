package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Set;

import java.util.ArrayList;
import java.util.List;

public class Sets {

    private List<Set> sets;

    public Sets(List<Set> sets) {
        this.sets = sets;
    }

    public static Sets empty() {
        return new Sets(new ArrayList<>());
    }

    public List<Set> getSets() {
        return sets;
    }

    public void add(Set set) {
        this.sets.add(set);
    }
}
