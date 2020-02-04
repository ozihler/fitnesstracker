package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsInOverviewViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.DisplayableWorkouts;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsInOverviewPresenter;
import org.springframework.http.ResponseEntity;

public class RestWorkoutsInOverviewPresenter extends RestPresenter<WorkoutsInOverviewViewModel> implements WorkoutsInOverviewPresenter {


    @Override
    public void present(DisplayableWorkouts workouts) {
        this.response = ResponseEntity.ok(toViewModel(workouts));
    }

    private WorkoutsInOverviewViewModel toViewModel(DisplayableWorkouts workouts) {
        var output = new WorkoutsInOverviewOutput(workouts);

        return output.workouts();
    }
}
