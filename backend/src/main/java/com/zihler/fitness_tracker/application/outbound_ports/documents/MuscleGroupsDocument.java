package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MuscleGroupsDocument {
    private Set<MuscleGroupDocument> muscleGroups;

    public MuscleGroupsDocument(Set<MuscleGroupDocument> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public MuscleGroupsDocument() {
        this(new HashSet<>());
    }

    public static MuscleGroupsDocument of(Set<MuscleGroupDocument> documents) {
        return new MuscleGroupsDocument(documents);
    }

    static MuscleGroupsDocument of(MuscleGroups muscleGroups) {
        return new MuscleGroupsDocument(muscleGroups
                .getMuscleGroups()
                .stream()
                .map(MuscleGroupDocument::of)
                .collect(toSet()));
    }

    public Set<MuscleGroupDocument> getMuscleGroups() {
        return muscleGroups;
    }

    public void add(MuscleGroupDocument muscleGroup) {
        muscleGroups.add(muscleGroup);
    }

    public int count() {
        return muscleGroups.size();
    }
}
