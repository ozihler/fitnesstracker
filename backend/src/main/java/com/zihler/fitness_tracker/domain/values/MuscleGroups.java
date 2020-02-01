package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import org.apache.logging.log4j.util.Strings;

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

    public void add(MuscleGroup muscleGroup) {
        muscleGroups.add(muscleGroup);
    }

    @Override
    public String toString() {
        return "MuscleGroups[" + Strings.join(muscleGroups,',') +"]";
    }
}
