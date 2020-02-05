package com.zihler.fitness_tracker.application.outbound_ports.documents;

import java.util.HashSet;
import java.util.Set;

public class ExercisesDocument {
    private Set<ExerciseDocument> exercises;

    private ExercisesDocument(Set<ExerciseDocument> exercises) {
        this.exercises = exercises;
    }

    ExercisesDocument() {
        this(new HashSet<>());
    }

    public static ExercisesDocument of(Set<ExerciseDocument> exercises) {
        return new ExercisesDocument(exercises);
    }

    public Set<ExerciseDocument> getExercises() {
        return exercises;
    }

    public void add(ExerciseDocument exercise) {
        exercises.add(exercise);
    }
}
