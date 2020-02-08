package com.zihler.fitness_tracker.domain.values;

import java.util.Objects;

public class ExerciseName {

    private Name exerciseName;

    private ExerciseName(String exerciseName) {
        this.exerciseName = Name.of(exerciseName);
    }

    public static ExerciseName of(String exerciseName) {
        return new ExerciseName(exerciseName);
    }

    @Override
    public String toString() {
        return exerciseName.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExerciseName that = (ExerciseName) o;
        return Objects.equals(exerciseName, that.exerciseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseName);
    }

    public Name getName() {
        return exerciseName;
    }
}
