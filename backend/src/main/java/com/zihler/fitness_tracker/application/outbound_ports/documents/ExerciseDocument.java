package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.Multiplier;
import com.zihler.fitness_tracker.domain.values.Name;

import static java.util.stream.Collectors.toList;


public class ExerciseDocument {
    private Name name;
    private SetsDocument sets;
    private Multiplier multiplier;


    private ExerciseDocument(Name name, SetsDocument sets) {
        this.name = name;
        this.sets = sets;
        this.multiplier = null;
    }

    public ExerciseDocument(Name name) {
        this(name, new SetsDocument());
    }

    public static ExerciseDocument of(Name name, SetsDocument sets) {
        return new ExerciseDocument(name, sets);
    }

    public static ExerciseDocument of(Name name) {
        return of(name, SetsDocument.empty());
    }

    public static ExerciseDocument of(Exercise e) {
        return new ExerciseDocument(e.getName(), SetsDocument.of(e.getSets().getSets().stream().map(s -> SetDocument.of(s.getWeight(), s.getRepetitions(), s.getWaitingTime())).collect(toList())));
    }

    public Name getName() {
        return name;
    }

    public SetsDocument getSets() {
        return sets;
    }

    public ExerciseDocument add(SetDocument set) {
        var sets = new SetsDocument();
        sets.add(set);
        return new ExerciseDocument(name, sets);
    }

    public Multiplier getMultiplier() {
        return this.multiplier;
    }
}
