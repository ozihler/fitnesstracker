package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.WorkoutFullOutput;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import org.springframework.http.ResponseEntity;

public class RestUpdatedWorkoutPresenter
        extends RestPresenter<WorkoutFullViewModel>
        implements WorkoutPresenter {

    @Override
    public void present(WorkoutDocument workoutDocument) {
        var output = new WorkoutFullOutput(workoutDocument);

        response = ResponseEntity.ok(output.toViewModel());
    }

}
