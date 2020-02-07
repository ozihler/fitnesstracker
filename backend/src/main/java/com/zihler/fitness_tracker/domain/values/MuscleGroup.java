package com.zihler.fitness_tracker.domain.values;

import java.util.Objects;

public class MuscleGroup {
    private Name name;
    private Exercises exercises;

    public MuscleGroup(Name name) {
        this.name = name;
        exercises = Exercises.empty();
    }

    public Name getName() {
        return name;
    }

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
}