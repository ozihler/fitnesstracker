package com.zihler.fitness_tracker.domain.values;


import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

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

    // todo kill this
    public void add(MuscleGroup muscleGroup) {
        muscleGroups.add(muscleGroup);
    }

    @Override
    public String toString() {
        return toNameString(",");
    }

    public String toNameString(String delimiter) {
        return muscleGroups.stream()
                .map(MuscleGroup::getName)
                .map(Name::toString)
                .filter(StringUtils::isNoneBlank)
                .collect(joining(delimiter));
    }
}
