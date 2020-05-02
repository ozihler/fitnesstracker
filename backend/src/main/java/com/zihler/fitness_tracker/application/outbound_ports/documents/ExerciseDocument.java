package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.values.Multiplier;
import com.zihler.fitness_tracker.domain.values.Name;


public class ExerciseDocument {
    private Name name;
    private SetsDocument sets;
    private Multiplier multiplier;

    public ExerciseDocument(Name name, SetsDocument sets, Multiplier multiplier) {
        this.name = name;
        this.sets = sets;
        this.multiplier = multiplier;
    }

    public Name getName() {
        return name;
    }

    public SetsDocument getSets() {
        return sets;
    }

    public Multiplier getMultiplier() {
        return this.multiplier;
    }
}
