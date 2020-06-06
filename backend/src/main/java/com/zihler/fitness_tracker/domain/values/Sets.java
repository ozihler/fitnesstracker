package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Set;

import java.util.ArrayList;
import java.util.List;

public class Sets {

    private final List<Set> sets;

    private Sets(List<Set> sets) {
        this.sets = sets;
    }

    public static Sets empty() {
        return new Sets(new ArrayList<>());
    }

    public static Sets of(List<Set> sets) {
        return new Sets(sets);
    }

    public static Sets of(Set... sets) {
        return new Sets(List.of(sets));
    }

    public List<Set> getSets() {
        return sets;
    }

    public void add(Set set) {
        sets.add(set);
    }
}
