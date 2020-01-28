package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MuscleGroupsDocument {
    private Set<MuscleGroupDocument> muscleGroups;

    public MuscleGroupsDocument(Set<MuscleGroupDocument> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public static MuscleGroupsDocument of(Set<MuscleGroupDocument> documents) {
        return new MuscleGroupsDocument(documents);
    }

    public Set<MuscleGroupDocument> getMuscleGroups() {
        return muscleGroups;
    }

}
