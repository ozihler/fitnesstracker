package com.zihler.fitness_tracker.application.outbound_ports.documents;


import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;

import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class MuscleGroupDocument {
    private Name name;
    private ExercisesDocument exercises;

    public ExercisesDocument getExercises() {
        return exercises;
    }

    public MuscleGroupDocument(Name name) {
        this(name, new ExercisesDocument());
    }

    public MuscleGroupDocument(Name name, ExercisesDocument exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public static MuscleGroupDocument of(MuscleGroup muscleGroup) {
        return new MuscleGroupDocument(muscleGroup.getName(),
                ExercisesDocument.of(
                        muscleGroup.getExercises()
                                .getExercises()
                                .stream()
                                .map(e ->
                                        new ExerciseDocument(
                                                e.getName(),
                                                SetsDocument.of(e.getSets().getSets().stream().map(s -> SetDocument.of(s.getWeight(), s.getRepetitions(), s.getWaitingTime())).collect(toList())),
                                                e.getMultiplier()))
                                .collect(toList())));
    }

    public static MuscleGroupDocument of(Name name) {
        return new MuscleGroupDocument(name);
    }

    public Name getName() {
        return name;
    }

    public MuscleGroupDocument add(ExerciseDocument exercise) {
        ExercisesDocument exercises = new ExercisesDocument();
        exercises.add(exercise);
        return new MuscleGroupDocument(name, exercises);
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MuscleGroupDocument that = (MuscleGroupDocument) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
