package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.MuscleGroup;

import java.util.Set;

public class MuscleGroups {
    private Set<MuscleGroup> muscleGroups;

    public MuscleGroups(Set<MuscleGroup> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public static MuscleGroups of(Set<MuscleGroup> muscleGroups) {
        return new MuscleGroups(muscleGroups);
    }

    public Set<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }
}
