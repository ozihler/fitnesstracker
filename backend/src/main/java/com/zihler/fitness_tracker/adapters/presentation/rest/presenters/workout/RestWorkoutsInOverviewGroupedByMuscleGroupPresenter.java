package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.WorkoutsInOverviewGroupedByMuscleGroupOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsInOverviewGroupedByMuscleGroupViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsPresenter;
import org.springframework.http.ResponseEntity;

public class RestWorkoutsInOverviewGroupedByMuscleGroupPresenter
        extends RestPresenter<WorkoutsInOverviewGroupedByMuscleGroupViewModel>
        implements WorkoutsPresenter {

    @Override
    public void present(WorkoutsDocument workouts) {
        var output = new WorkoutsInOverviewGroupedByMuscleGroupOutput(workouts);

        this.response = ResponseEntity.ok(output.workoutsGroupedByMuscleGroup());
    }

}
