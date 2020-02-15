package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Set;

import java.util.Objects;

public class Exercise {
    private Name name;
    private Sets sets;
    private boolean isSelectable = true;

    public Exercise(Name name) {
        this(name, Sets.empty());
    }

    private Exercise(Name name, Sets sets) {
        this.name = name;
        this.sets = sets;
    }

    public static Exercise of(String name, Sets sets) {
        return new Exercise(Name.of(name), sets);
    }

    public static Exercise of(String name) {
        return new Exercise(Name.of(name));
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

    public void setSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean isSelectable() {
        return isSelectable;
    }
}
