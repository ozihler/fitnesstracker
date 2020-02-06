package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.exceptions.InvalidWeightException;

public class Weight {
    private float weight;
    private UnitOfMeasurement unitOfMeasurement;

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
}
