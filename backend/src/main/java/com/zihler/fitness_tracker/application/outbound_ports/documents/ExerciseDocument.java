package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.values.Name;

public class ExerciseDocument {
    private Name name;
    private SetsDocument sets;


    private ExerciseDocument(Name name, SetsDocument sets) {
        this.name = name;
        this.sets = sets;
    }

    public static ExerciseDocument of(Name name, SetsDocument sets) {
        return new ExerciseDocument(name, sets);
    }

    public static ExerciseDocument of(Name name) {
        return new ExerciseDocument(name, SetsDocument.empty());
    }

    public Name getName() {
        return name;
    }

    public SetsDocument getSets() {
        return sets;
    }
}
