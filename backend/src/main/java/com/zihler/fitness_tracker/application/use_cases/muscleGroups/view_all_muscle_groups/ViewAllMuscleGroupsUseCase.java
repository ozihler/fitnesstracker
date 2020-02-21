package com.zihler.fitness_tracker.application.use_cases.muscleGroups.view_all_muscle_groups;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchAllMuscleGroups;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;
import com.zihler.fitness_tracker.application.use_cases.muscleGroups.view_all_muscle_groups.inbound_port.ViewAllMuscleGroups;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import static java.util.stream.Collectors.toList;

public class ViewAllMuscleGroupsUseCase implements ViewAllMuscleGroups {
    private FetchAllMuscleGroups fetchMuscleGroups;

    public ViewAllMuscleGroupsUseCase(FetchAllMuscleGroups fetchMuscleGroups) {
        this.fetchMuscleGroups = fetchMuscleGroups;
    }

    private static MuscleGroupsDocument toDocument(MuscleGroups muscleGroups) {
        return new MuscleGroupsDocument(muscleGroups.getMuscleGroups().stream().map(MuscleGroupDocument::of).collect(toList()));
    }

    @Override
    public void invokeWith(MuscleGroupsPresenter presenter) {
        MuscleGroups muscleGroups = fetchMuscleGroups.fetchAll();

        presenter.present(toDocument(muscleGroups));
    }
}
