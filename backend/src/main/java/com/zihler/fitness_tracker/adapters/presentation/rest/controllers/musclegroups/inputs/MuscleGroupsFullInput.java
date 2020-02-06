package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.MuscleGroupFullViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.domain.values.Name;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MuscleGroupsFullInput {
    private Set<MuscleGroupFullViewModel> muscleGroups;

    public MuscleGroupsFullInput(Set<MuscleGroupFullViewModel> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }


    public MuscleGroupsDocument toDocument() {
        return MuscleGroupsDocument.of(muscleGroups
                .stream()
                .map(m -> new MuscleGroupDocument(Name.of(m.getName()), new ExercisesFullInput(m.getExercises()).toDocument()))
                .collect(toSet()));
    }

}
