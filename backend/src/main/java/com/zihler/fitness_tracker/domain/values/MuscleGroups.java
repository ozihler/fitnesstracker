package com.zihler.fitness_tracker.domain.values;

import org.apache.logging.log4j.util.Strings;

import java.util.HashSet;
import java.util.Set;

public class MuscleGroups {
    private Set<MuscleGroup> muscleGroups;

    public MuscleGroups(Set<MuscleGroup> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public MuscleGroups() {
        this(new HashSet<>());
    }

    public static MuscleGroups of(Set<MuscleGroup> muscleGroups) {
        return new MuscleGroups(muscleGroups);
    }

    public static MuscleGroups muscleGroups(MuscleGroup... muscleGroups) {
        return of(Set.of(muscleGroups));
    }

    public Set<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    public void add(MuscleGroup muscleGroup) {
        muscleGroups.add(muscleGroup);
    }

    @Override
    public String toString() {
        return "MuscleGroups[" + Strings.join(muscleGroups, ',') + "]";
    }

}
