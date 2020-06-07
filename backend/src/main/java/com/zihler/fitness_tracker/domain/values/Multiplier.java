package com.zihler.fitness_tracker.domain.values;

public enum Multiplier {
    ONE(1), TWO(2);

    private final int value;

    Multiplier(int value) {
        this.value = value;
    }

    public static Multiplier of(int multiplier) {
        if (multiplier == 2) {
            return TWO;
        }
        return ONE;
    }

    public static Multiplier of(String multiplier) {
        return of(Integer.parseInt(multiplier));
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
