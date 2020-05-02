package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.WorkoutsInOverviewOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsInOverviewViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsPresenter;
import org.springframework.http.ResponseEntity;

public class RestWorkoutsPresenter
        extends RestPresenter<WorkoutsInOverviewViewModel>
        implements WorkoutsPresenter {

    @Override
    public void present(WorkoutsDocument workouts) {
        var output = new WorkoutsInOverviewOutput(workouts);

        this.response = ResponseEntity.ok(output.workouts());
    }

}
