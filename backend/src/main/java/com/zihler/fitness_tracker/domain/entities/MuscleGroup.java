package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.Name;

import java.util.Objects;

public class MuscleGroup {
    private Name name;

    public MuscleGroup(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MuscleGroup that = (MuscleGroup) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
