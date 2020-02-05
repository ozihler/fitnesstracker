package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.WorkoutOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutAndMuscleGroupNamesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import org.springframework.http.ResponseEntity;

public class RestWorkoutPresenter
        extends RestPresenter<WorkoutAndMuscleGroupNamesViewModel>
        implements WorkoutPresenter {

    @Override
    public void present(WorkoutDocument workoutDocument) {
        var output = new WorkoutOutput(workoutDocument);

        response = ResponseEntity.ok(output.toViewModel());
    }

}
