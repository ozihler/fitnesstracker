package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs.MuscleGroupsInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.CreateMuscleGroupsRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.RestMuscleGroupsPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreMuscleGroups;
import com.zihler.fitness_tracker.application.use_cases.muscleGroups.create_muscle_groups.CreateMuscleGroupsUseCase;
import com.zihler.fitness_tracker.application.use_cases.muscleGroups.create_muscle_groups.inbound_port.CreateMuscleGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CreateMuscleGroupsController {

    private final CreateMuscleGroups createMuscleGroups;

    @Autowired
    public CreateMuscleGroupsController(StoreMuscleGroups storeMuscleGroups) {
        createMuscleGroups = new CreateMuscleGroupsUseCase(storeMuscleGroups);
    }

    public ResponseEntity<MuscleGroupsViewModel> createMuscleGroups(CreateMuscleGroupsRequest request) {
        var input = new MuscleGroupsInput(request);
        var output = new RestMuscleGroupsPresenter();

        createMuscleGroups.callWith(input.muscleGroups(), output);

        return output.getResponse();
    }
}
