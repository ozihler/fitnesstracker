package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Exercise;

import java.util.Set;

public class Exercises {
    Set<Exercise> exercises;

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void add(Exercise exercise) {
        this.exercises.add(exercise);
    }
}
