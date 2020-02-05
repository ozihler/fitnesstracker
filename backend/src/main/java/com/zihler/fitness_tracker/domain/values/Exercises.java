package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Exercise;

import java.util.HashSet;
import java.util.Set;

public class Exercises {
    private Set<Exercise> exercises;

    public Exercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public static Exercises of(Set<Exercise> exercises) {
        return new Exercises(exercises);
    }

    public static Exercises empty() {
        return new Exercises(new HashSet<>());
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void add(Exercise exercise) {
        exercises.add(exercise);
    }

}
