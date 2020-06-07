package com.zihler.fitness_tracker.application.use_cases.muscleGroups.make_muscle_group_unselectable;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupPresenter;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreMuscleGroup;
import com.zihler.fitness_tracker.application.use_cases.muscleGroups.make_muscle_group_unselectable.inbound_port.MakeMuscleGroupUnselectable;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroupName;

public class MakeMuscleGroupUnselectableUseCase implements MakeMuscleGroupUnselectable {
    private final FetchMuscleGroup fetchMuscleGroup;
    private final StoreMuscleGroup storeMuscleGroup;

    public MakeMuscleGroupUnselectableUseCase(
            FetchMuscleGroup fetchMuscleGroup,
            StoreMuscleGroup storeMuscleGroup) {
        this.fetchMuscleGroup = fetchMuscleGroup;
        this.storeMuscleGroup = storeMuscleGroup;
    }

    @Override
    public void invokeWith(MuscleGroupName muscleGroupName, MuscleGroupPresenter output) {
        MuscleGroup muscleGroup = fetchMuscleGroup.by(muscleGroupName.getName());
        muscleGroup.setSelectable(false);
        MuscleGroup storedMuscleGroup = storeMuscleGroup.withValues(muscleGroup);
        MuscleGroupDocument document = MuscleGroupDocument.of(storedMuscleGroup);
        output.present(document);
    }
}
