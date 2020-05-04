package com.zihler.fitness_tracker.application.use_cases.muscleGroups.create_muscle_groups;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreMuscleGroups;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;
import com.zihler.fitness_tracker.application.use_cases.muscleGroups.create_muscle_groups.inbound_port.CreateMuscleGroups;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CreateMuscleGroupsUseCase implements CreateMuscleGroups {
    private StoreMuscleGroups store;

    public CreateMuscleGroupsUseCase(StoreMuscleGroups store) {
        this.store = store;
    }

    @Override
    public void callWith(MuscleGroupsDocument muscleGroupsDocument, MuscleGroupsPresenter presenter) {
        MuscleGroups muscleGroups = new MuscleGroups(toEntities(muscleGroupsDocument));

        MuscleGroups storedMuscleGroups = store.withValues(muscleGroups);

        presenter.present(toDocument(storedMuscleGroups));
    }

    private MuscleGroupsDocument toDocument(MuscleGroups muscleGroups) {
        return MuscleGroupsDocument.of(muscleGroups.getMuscleGroups().stream()
                .map(MuscleGroupDocument::of)
                .collect(toList()));
    }

    private List<MuscleGroup> toEntities(MuscleGroupsDocument muscleGroupsDocument) {
        return muscleGroupsDocument.getMuscleGroups()
                .stream()
                .map(MuscleGroupDocument::getName)
                .map(name -> new MuscleGroup(name, Exercises.empty()))
                .distinct()
                .collect(toList());
    }
}
