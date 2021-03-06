package com.zihler.fitness_tracker.domain.entities;


import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.Name;

import java.util.Objects;

public class MuscleGroup {
    // replace with muscle group name
    private final Name name;
    private final Exercises exercises;
    private boolean isSelectable = true;

    public MuscleGroup(Name name, Exercises exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public Name getName() {
        return name;
    }

    //todo this needs to die!!
    public void add(Exercise exercise) {
        exercises.add(exercise);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MuscleGroup that = (MuscleGroup) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Exercises getExercises() {
        return exercises;
    }

    public void add(Exercises exercises) {
        exercises.getExercises().forEach(this::add);
    }

    public void setSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

}
