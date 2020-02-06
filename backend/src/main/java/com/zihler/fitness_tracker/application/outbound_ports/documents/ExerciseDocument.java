package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.Name;

import static java.util.stream.Collectors.toList;


public class ExerciseDocument {
    private Name name;
    private SetsDocument sets;
    private MuscleGroupDocument muscleGroup;


    private ExerciseDocument(MuscleGroupDocument muscleGroup, Name name, SetsDocument sets) {
        this.name = name;
        this.sets = sets;
        this.muscleGroup = muscleGroup;
    }

    public ExerciseDocument(Name name) {
        this(null, name, new SetsDocument());
    }

    private ExerciseDocument(Name name, SetsDocument sets) {
        this(null, name, sets);
    }

    public static ExerciseDocument of(MuscleGroupDocument muscleGroup, Name name, SetsDocument sets) {
        return new ExerciseDocument(muscleGroup, name, sets);
    }

    public static ExerciseDocument of(MuscleGroupDocument muscleGroup, Name name) {
        return of(muscleGroup, name, SetsDocument.empty());
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

    public MuscleGroupDocument getMuscleGroup() {
        return muscleGroup;
    }

    public ExerciseDocument add(SetDocument set) {
        var sets = new SetsDocument();
        sets.add(set);
        return new ExerciseDocument(name, sets);
    }
}
