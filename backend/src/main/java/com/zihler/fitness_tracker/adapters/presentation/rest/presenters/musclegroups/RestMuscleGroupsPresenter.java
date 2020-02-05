package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs.MuscleGroupsOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;
import org.springframework.http.ResponseEntity;

public class RestMuscleGroupsPresenter
        extends RestPresenter<MuscleGroupsViewModel>
        implements MuscleGroupsPresenter {

    @Override
    public void present(MuscleGroupsDocument muscleGroups) {
        var output = new MuscleGroupsOutput(muscleGroups);

        this.response = ResponseEntity.ok(output.toViewModel());
    }
}
