package com.zihler.fitness_tracker.domain.values;


import com.zihler.fitness_tracker.domain.entities.MuscleGroup;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class MuscleGroups {
    private final List<MuscleGroup> muscleGroups;

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
        return "MuscleGroups[" +  muscleGroups.stream().map(Object::toString).collect( joining(", ")) + "]";
    }

}
