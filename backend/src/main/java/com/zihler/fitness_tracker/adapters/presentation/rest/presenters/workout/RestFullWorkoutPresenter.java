package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullWorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.FullWorkoutOutput;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import org.springframework.http.ResponseEntity;

public class RestFullWorkoutPresenter
        extends RestPresenter<FullWorkoutViewModel>
        implements WorkoutPresenter {

    @Override
    public void present(WorkoutDocument workoutDocument) {
        var output = new FullWorkoutOutput(workoutDocument);

        response = ResponseEntity.ok(output.toViewModel());
    }

}
