package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.domain.values.Name;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MuscleGroupsFullInput {
    private List<MuscleGroupViewModel> muscleGroups;

    public MuscleGroupsFullInput(List<MuscleGroupViewModel> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }


    public MuscleGroupsDocument toDocument() {
        return MuscleGroupsDocument.of(muscleGroups
                .stream()
                .map(m -> new MuscleGroupDocument(Name.of(m.getName()), new ExercisesFullInput(m.getExercises()).toDocument(), m.getIsSelectable()))
                .collect(toList()));
    }

}
