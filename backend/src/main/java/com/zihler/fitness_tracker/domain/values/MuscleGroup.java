package com.zihler.fitness_tracker.domain.values;


import java.util.Arrays;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class MuscleGroup {
    // replace with muscle group name
    private Name name;
    private Exercises exercises;
    private boolean isSelectable = true;

    private MuscleGroup(Name name, Exercises exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    private MuscleGroup() {
    }

    public static MuscleGroup of(Name name) {
        return of(name, Exercises.empty());
    }

    public static MuscleGroup of(Name name, Exercises exercises) {
        return new MuscleGroup(name, exercises);
    }

    public static MuscleGroup of(String name, Exercises exercises) {
        return of(Name.of(name), exercises);
    }

    public static MuscleGroup of(String name, Exercise... exercises) {
        return of(name, Exercises.of(exercises));
    }

    public static MuscleGroup withoutSets(String name, String... exerciseNames) {
        return of(name, Exercises.of(Arrays.stream(exerciseNames).map(ExerciseInput::of).collect(toList())));
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

    public void setSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

}
