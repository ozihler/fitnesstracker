package com.zihler.fitness_tracker.application.use_cases.view_all_muscle_groups;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchAllMuscleGroups;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;
import com.zihler.fitness_tracker.application.use_cases.view_all_muscle_groups.inbound_port.ViewAllMuscleGroups;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import static java.util.stream.Collectors.toSet;

public class ViewAllMuscleGroupsUseCase implements ViewAllMuscleGroups {
    FetchAllMuscleGroups fetchMuscleGroups;

    public ViewAllMuscleGroupsUseCase(FetchAllMuscleGroups fetchMuscleGroups) {
        this.fetchMuscleGroups = fetchMuscleGroups;
    }

    public static MuscleGroupsDocument toDocument(MuscleGroups muscleGroups) {
        return new MuscleGroupsDocument(muscleGroups.getMuscleGroups().stream().map(MuscleGroupDocument::of).collect(toSet()));
    }

    @Override
    public void fetch(MuscleGroupsPresenter presenter) {
        MuscleGroups muscleGroups = this.fetchMuscleGroups.fetchAll();
        presenter.present(toDocument(muscleGroups));
    }
}
