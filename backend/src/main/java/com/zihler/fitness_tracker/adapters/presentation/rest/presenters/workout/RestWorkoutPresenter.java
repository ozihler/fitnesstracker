package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import org.springframework.http.ResponseEntity;

public class RestWorkoutPresenter implements WorkoutPresenter {
    private ResponseEntity<WorkoutViewModel> response;

    @Override
    public void present(WorkoutDocument workoutDocument) {
        var output = new WorkoutOutput(workoutDocument);
        this.response = ResponseEntity.ok(output.toViewModel());
    }

    public ResponseEntity<WorkoutViewModel> getResponse() {
        return response;
    }
}
