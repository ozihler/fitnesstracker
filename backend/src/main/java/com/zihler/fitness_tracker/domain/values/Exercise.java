package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Set;

public class Exercise {
    private Name name;
    private Sets sets;

    public Exercise(Name name) {
        this(name, Sets.empty());
    }

    private Exercise(Name name, Sets sets) {
        this.name = name;
        this.sets = sets;
    }

    public void add(Set set) {
        sets.add(set);
    }

    public Name getName() {
        return name;
    }

    public Sets getSets() {
        return sets;
    }

    public void addAll(Sets sets) {
        this.sets = sets;
    }
}
