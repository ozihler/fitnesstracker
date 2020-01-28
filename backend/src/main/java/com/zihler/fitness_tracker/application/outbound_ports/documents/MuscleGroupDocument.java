package com.zihler.fitness_tracker.application.outbound_ports.documents;


import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;

public class MuscleGroupDocument {
    private Name name;

    private MuscleGroupDocument(Name name) {
        this.name = name;
    }

    public static MuscleGroupDocument of(MuscleGroup muscleGroup) {
        return new MuscleGroupDocument(muscleGroup.getName());
    }

    public Name getName() {
        return this.name;
    }
}
