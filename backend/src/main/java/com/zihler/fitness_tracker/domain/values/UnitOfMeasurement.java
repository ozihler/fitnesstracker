package com.zihler.fitness_tracker.domain.values;

public enum UnitOfMeasurement {
    KILOGRAMM("kg");

    private String shortname;

    UnitOfMeasurement(String shortname) {
        this.shortname = shortname;
    }

    public String shortname() {
        return shortname;
    }
}
