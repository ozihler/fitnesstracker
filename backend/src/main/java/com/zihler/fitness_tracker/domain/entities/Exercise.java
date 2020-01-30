package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Sets;

import java.util.ArrayList;

public class Exercise {
    private Name name;
    private Sets sets;

    public Exercise(Name name) {
        this(name, Sets.empty());
    }

    public Exercise(Name name, Sets sets) {
        this.name = name;
        this.sets = sets;
    }

    public void add(Set set) {
        this.sets.add(set);
    }
    public Name getName() {
        return name;
    }

    public Sets getSets() {
        return sets;
    }
}
