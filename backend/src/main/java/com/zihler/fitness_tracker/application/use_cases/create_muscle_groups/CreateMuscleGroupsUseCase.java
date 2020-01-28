package com.zihler.fitness_tracker.application.use_cases.create_muscle_groups;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreMuscleGroups;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;
import com.zihler.fitness_tracker.application.use_cases.create_muscle_groups.inbound_port.CreateMuscleGroups;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class CreateMuscleGroupsUseCase implements CreateMuscleGroups {
    private StoreMuscleGroups store;

    public CreateMuscleGroupsUseCase(StoreMuscleGroups store) {
        this.store = store;
    }

    @Override
    public void callWith(MuscleGroupsDocument muscleGroupsDocument, MuscleGroupsPresenter presenter) {
        MuscleGroups muscleGroups = new MuscleGroups(toEntities(muscleGroupsDocument));

        MuscleGroups storedMuscleGroups = this.store.as(muscleGroups);

        presenter.present(toDocument(storedMuscleGroups));
    }

    private MuscleGroupsDocument toDocument(MuscleGroups muscleGroups) {
        return MuscleGroupsDocument.of(muscleGroups.getMuscleGroups().stream()
                .map(MuscleGroupDocument::of)
                .collect(toSet()));
    }

    private Set<MuscleGroup> toEntities(MuscleGroupsDocument muscleGroupsDocument) {
        return muscleGroupsDocument.getMuscleGroups()
                    .stream()
                    .map(MuscleGroupDocument::getName)
                    .map(MuscleGroup::new)
                    .collect(toSet());
    }
}
