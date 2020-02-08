package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.RestMuscleGroupsPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchAllMuscleGroups;
import com.zihler.fitness_tracker.application.use_cases.muscleGroups.view_all_muscle_groups.ViewAllMuscleGroupsUseCase;
import com.zihler.fitness_tracker.application.use_cases.muscleGroups.view_all_muscle_groups.inbound_port.ViewAllMuscleGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ViewMuscleGroupsController {
    private ViewAllMuscleGroups viewAllMuscleGroups;

    @Autowired
    public ViewMuscleGroupsController(FetchAllMuscleGroups fetchMuscleGroups) {
        viewAllMuscleGroups = new ViewAllMuscleGroupsUseCase(fetchMuscleGroups);
    }

    public ResponseEntity<MuscleGroupsViewModel> viewAllMuscleGroups() {
        var output = new RestMuscleGroupsPresenter();

        viewAllMuscleGroups.invokeWith(output);

        return output.getResponse();
    }
}
