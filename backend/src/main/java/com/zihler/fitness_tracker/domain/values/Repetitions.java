package com.zihler.fitness_tracker.domain.values;

import java.util.Objects;

public class Repetitions {
    private final int numberOfRepetitions;

    private Repetitions(int numberOfRepetitions) {
        this.numberOfRepetitions = numberOfRepetitions;
    }

    public static Repetitions of(int numberOfRepetitions) {
        return new Repetitions(numberOfRepetitions);
    }

    public int number() {
        return numberOfRepetitions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repetitions that = (Repetitions) o;
        return numberOfRepetitions == that.numberOfRepetitions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfRepetitions);
    }

    @Override
    public String toString() {
        return "Repetitions{" +
                "numberOfRepetitions=" + numberOfRepetitions +
                '}';
    }
}
