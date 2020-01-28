package com.zihler.fitness_tracker.application.outbound_ports.documents;


import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;

import java.util.Objects;

public class MuscleGroupDocument {
    private Name name;

    private MuscleGroupDocument(Name name) {
        this.name = name;
    }

    public static MuscleGroupDocument of(MuscleGroup muscleGroup) {
        return new MuscleGroupDocument(muscleGroup.getName());
    }

    public static MuscleGroupDocument of(Name name) {
        return new MuscleGroupDocument(name);
    }

    public Name getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MuscleGroupDocument that = (MuscleGroupDocument) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
