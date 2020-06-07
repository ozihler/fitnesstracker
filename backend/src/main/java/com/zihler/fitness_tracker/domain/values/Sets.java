package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Set;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sets sets1 = (Sets) o;
        return Objects.equals(sets, sets1.sets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sets);
    }
}
