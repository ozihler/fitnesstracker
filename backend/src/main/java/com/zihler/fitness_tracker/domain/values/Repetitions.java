package com.zihler.fitness_tracker.domain.values;

public class Repetitions {
    private int numberOfRepetitions;

    private Repetitions(int numberOfRepetitions) {
        this.numberOfRepetitions = numberOfRepetitions;
    }

    public static Repetitions of(int numberOfRepetitions) {
        return new Repetitions(numberOfRepetitions);
    }

    public int number() {
        return numberOfRepetitions;
    }
}
