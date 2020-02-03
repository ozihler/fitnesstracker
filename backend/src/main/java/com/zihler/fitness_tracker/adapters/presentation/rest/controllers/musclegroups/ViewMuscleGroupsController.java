package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchAllMuscleGroups;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;
import com.zihler.fitness_tracker.application.use_cases.view_all_muscle_groups.inbound_port.ViewAllMuscleGroups;
import com.zihler.fitness_tracker.application.use_cases.view_all_muscle_groups.ViewAllMuscleGroupsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewMuscleGroupsController {
    private ViewAllMuscleGroups viewAllMuscleGroups;

    @Autowired
    public ViewMuscleGroupsController(FetchAllMuscleGroups fetchMuscleGroups) {
        this.viewAllMuscleGroups = new ViewAllMuscleGroupsUseCase(fetchMuscleGroups);
    }

    public void viewAllMuscleGroups(MuscleGroupsPresenter presenter) {
        this.viewAllMuscleGroups.invokeWith(presenter);
    }
}