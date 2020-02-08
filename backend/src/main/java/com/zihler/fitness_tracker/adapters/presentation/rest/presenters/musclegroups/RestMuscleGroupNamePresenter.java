package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs.MuscleGroupNameOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupNameViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;

public class RestMuscleGroupNamePresenter
        extends RestPresenter<MuscleGroupNameViewModel>
        implements MuscleGroupPresenter {
    @Override
    public void present(MuscleGroupDocument document) {
        var output = new MuscleGroupNameOutput(document.getName());

        response = output.toViewModel();
    }
}
