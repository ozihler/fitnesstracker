package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.exceptions.InvalidWeightException;

import java.util.Objects;

public class Weight {
    private final float weight;
    private final UnitOfMeasurement unitOfMeasurement;

    private Weight(float weight, UnitOfMeasurement unitOfMeasurement) {
        if (weight < 0) {
            throw new InvalidWeightException("Weight of (" + weight + ") forbidden. Needs to be positiv or at least 0");
        }
        this.weight = weight;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public static Weight of(float weight, UnitOfMeasurement unitOfMeasurement) {
        return new Weight(weight, unitOfMeasurement);
    }

    public float value() {
        return weight;
    }

    public UnitOfMeasurement unitOfMeasurement() {
        return unitOfMeasurement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight1 = (Weight) o;
        return Float.compare(weight1.weight, weight) == 0 &&
                unitOfMeasurement == weight1.unitOfMeasurement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, unitOfMeasurement);
    }

    @Override
    public String toString() {
        return "Weight{" +
                "weight=" + weight +
                ", unitOfMeasurement=" + unitOfMeasurement +
                '}';
    }
}
