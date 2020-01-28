package com.zihler.fitness_tracker.domain.values;

import static com.zihler.fitness_tracker.domain.values.UnitOfMeasurement.*;

public class Weight {
    private float weight;
    private UnitOfMeasurement unitOfMeasurement;

    private Weight(float weight, UnitOfMeasurement unitOfMeasurement) {
        this.weight = weight;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public static Weight of(float weight) {
        return new Weight(weight, KILOGRAMM);
    }

    public float value() {
        return weight;
    }

    public UnitOfMeasurement unitOfMeasurement() {
        return unitOfMeasurement;
    }
}
