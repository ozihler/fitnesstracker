package com.zihler.fitness_tracker.domain.values;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exercises {
    private final List<Exercise> exercises;

    private Exercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public static Exercises of(List<Exercise> exercises) {
        return new Exercises(exercises);
    }

    public static Exercises empty() {
        return new Exercises(new ArrayList<>());
    }

    public static Exercises of(Exercise... exercises) {
        return of(List.of(exercises));
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void add(Exercise exercise) {
        exercises.add(exercise);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercises exercises1 = (Exercises) o;
        return Objects.equals(exercises, exercises1.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exercises);
    }
}
