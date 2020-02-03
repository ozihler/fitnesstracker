package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs.MuscleGroupsInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.CreateMuscleGroupsRequest;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreMuscleGroups;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;
import com.zihler.fitness_tracker.application.use_cases.create_muscle_groups.CreateMuscleGroupsUseCase;
import com.zihler.fitness_tracker.application.use_cases.create_muscle_groups.inbound_port.CreateMuscleGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateMuscleGroupsController {

    private final CreateMuscleGroups createMuscleGroups;

    @Autowired
    public CreateMuscleGroupsController(StoreMuscleGroups storeMuscleGroups) {
        this.createMuscleGroups = new CreateMuscleGroupsUseCase(storeMuscleGroups);
    }

    public void createMuscleGroups(CreateMuscleGroupsRequest request, MuscleGroupsPresenter presenter) {
        var input = new MuscleGroupsInput(request);

        MuscleGroupsDocument muscleGroups = input.muscleGroups();

        this.createMuscleGroups.callWith(muscleGroups, presenter);

    }
}
