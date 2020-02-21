package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupsDocument {
    private List<MuscleGroupDocument> muscleGroups;

    public MuscleGroupsDocument(List<MuscleGroupDocument> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public MuscleGroupsDocument() {
        this(new ArrayList<>());
    }

    public static MuscleGroupsDocument of(List<MuscleGroupDocument> documents) {
        return new MuscleGroupsDocument(documents);
    }

    static MuscleGroupsDocument of(MuscleGroups muscleGroups) {
        return new MuscleGroupsDocument(muscleGroups
                .getMuscleGroups()
                .stream()
                .map(MuscleGroupDocument::of)
                .collect(toList()));
    }

    public List<MuscleGroupDocument> getMuscleGroups() {
        return muscleGroups;
    }

    public void add(MuscleGroupDocument muscleGroup) {
        muscleGroups.add(muscleGroup);
    }

    public int count() {
        return muscleGroups.size();
    }
}
