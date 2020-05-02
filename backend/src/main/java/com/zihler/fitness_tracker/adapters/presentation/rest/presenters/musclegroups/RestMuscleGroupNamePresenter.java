package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs.MuscleGroupNameOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import org.springframework.http.ResponseEntity;

public class RestMuscleGroupNamePresenter
        extends RestPresenter<MuscleGroupViewModel>
        implements MuscleGroupPresenter {
    @Override
    public void present(MuscleGroupDocument document) {
        var output = new MuscleGroupNameOutput(document.getName());

        //todo this needs to be done everywhere: output only produces view model, assignment of response entity happens in Presenter
        response = ResponseEntity.ok(output.toViewModel());
    }
}
