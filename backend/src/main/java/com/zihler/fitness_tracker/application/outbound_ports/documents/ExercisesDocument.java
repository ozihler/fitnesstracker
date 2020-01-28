package com.zihler.fitness_tracker.application.outbound_ports.documents;

import java.util.Set;

public class ExercisesDocument {
    Set<ExerciseDocument> exercises;

    private ExercisesDocument(Set<ExerciseDocument> exercises) {
        this.exercises = exercises;
    }

    public static ExercisesDocument of(Set<ExerciseDocument> exercises) {
        return new ExercisesDocument(exercises);
    }

    public Set<ExerciseDocument> getExercises() {
        return exercises;
    }
}
