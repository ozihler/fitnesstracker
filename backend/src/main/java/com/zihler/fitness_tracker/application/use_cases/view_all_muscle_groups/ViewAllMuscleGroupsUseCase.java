package com.zihler.fitness_tracker.application.use_cases.view_all_muscle_groups;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.IFetchMuscleGroups;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;
import com.zihler.fitness_tracker.application.use_cases.view_all_muscle_groups.inbound_port.ViewAllMuscleGroups;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

public class ViewAllMuscleGroupsUseCase implements ViewAllMuscleGroups {
    IFetchMuscleGroups fetchMuscleGroups;

    public ViewAllMuscleGroupsUseCase(IFetchMuscleGroups fetchMuscleGroups) {
        this.fetchMuscleGroups = fetchMuscleGroups;
    }

    @Override
    public void fetch(MuscleGroupsPresenter presenter) {
        MuscleGroups muscleGroups = this.fetchMuscleGroups.fetchAll();
        presenter.present(MuscleGroupsDocument.of(muscleGroups));
    }
}
