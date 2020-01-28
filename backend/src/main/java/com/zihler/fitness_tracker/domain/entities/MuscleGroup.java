package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.Name;

import java.util.Objects;

public class MuscleGroup {
    private Name name;
    private Exercises exercises;

    public MuscleGroup(Name name) {
        this.name = name;
        this.exercises = new Exercises();
    }

    public Name getName() {
        return name;
    }

    public void add(Exercise exercise) {
        this.exercises.add(exercise);
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

    public Exercises getExercises() {
        return exercises;
    }
}
