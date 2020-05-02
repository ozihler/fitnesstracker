package com.zihler.fitness_tracker.domain.values;

import java.util.Objects;

public class Multiplier {
    private final int value;

    private Multiplier(int value) {
        this.value = value;
    }

    public static Multiplier of(String valueAsString) {
        return of(Integer.parseInt(valueAsString));
    }

    public static Multiplier of(int value) {
        return new Multiplier(value);
    }

    public static Multiplier defaultValue() {
        return of(1);
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multiplier that = (Multiplier) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
