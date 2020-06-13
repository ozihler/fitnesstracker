package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.Multiplier;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Sets;

import java.util.Objects;

public class Exercise {
    private final Name name;
    private final Sets sets;
    private final Multiplier multiplier;
    private boolean isSelectable = true;

    public Exercise(Name name, Sets sets, Multiplier multiplier) {
        this.name = name;
        this.sets = sets;
        this.multiplier = multiplier;
    }


    public Name getName() {
        return name;
    }

    public Sets getSets() {
        return sets;
    }

    public Multiplier getMultiplier() {
        return multiplier;
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

    @Override
    public String toString() {
        return "Exercise{" +
                "name=" + name +
                ", sets=" + sets +
                ", multiplier=" + multiplier +
                ", isSelectable=" + isSelectable +
                '}';
    }
}
