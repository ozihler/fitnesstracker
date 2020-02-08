package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs.MuscleGroupNameInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.RestMuscleGroupPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupNameViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreMuscleGroup;
import com.zihler.fitness_tracker.application.use_cases.muscleGroups.make_muscle_group_unselectable.MakeMuscleGroupUnselectableUseCase;
import com.zihler.fitness_tracker.application.use_cases.muscleGroups.make_muscle_group_unselectable.inbound_port.MakeMuscleGroupUnselectable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MakeMuscleGroupUnselectableController {
    private MakeMuscleGroupUnselectable makeMuscleGroupUnselectable;

    @Autowired
    public MakeMuscleGroupUnselectableController(FetchMuscleGroup fetchMuscleGroup, StoreMuscleGroup storeMuscleGroup) {
        makeMuscleGroupUnselectable = new MakeMuscleGroupUnselectableUseCase(fetchMuscleGroup, storeMuscleGroup);
    }

    public ResponseEntity<MuscleGroupNameViewModel> makeUnselectable(String muscleGroupName) {
        var output = new RestMuscleGroupPresenter();
        var input = new MuscleGroupNameInput(muscleGroupName);

        makeMuscleGroupUnselectable.invokeWith(input.muscleGroupName(), output);

        return output.getResponse();
    }
}
