package com.zihler.fitness_tracker.application.outbound_ports.documents;

import java.util.ArrayList;
import java.util.List;

public class ExercisesDocument {
    private List<ExerciseDocument> exercises;

    private ExercisesDocument(List<ExerciseDocument> exercises) {
        this.exercises = exercises;
    }

    ExercisesDocument() {
        this(new ArrayList<>());
    }

    public static ExercisesDocument of(List<ExerciseDocument> exercises) {
        return new ExercisesDocument(exercises);
    }

    public List<ExerciseDocument> getExercises() {
        return exercises;
    }

    public void add(ExerciseDocument exercise) {
        exercises.add(exercise);
    }
}
