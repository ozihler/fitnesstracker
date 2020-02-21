package com.zihler.fitness_tracker.domain.values;

import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class MuscleGroups {
    private List<MuscleGroup> muscleGroups;

    public MuscleGroups(List<MuscleGroup> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public MuscleGroups() {
        this(new ArrayList<>());
    }

    public static MuscleGroups of(List<MuscleGroup> muscleGroups) {
        return new MuscleGroups(muscleGroups);
    }

    public static MuscleGroups muscleGroups(MuscleGroup... muscleGroups) {
        return of(List.of(muscleGroups));
    }

    public List<MuscleGroup> getMuscleGroups() {
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
