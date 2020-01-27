package com.zihler.fitness_tracker.adapters.presentation.rest.presenters;

import com.zihler.fitness_tracker.adapters.presentation.rest.json.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.output.WorkoutOutput;
import com.zihler.fitness_tracker.application.outbound_ports.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.WorkoutPresenter;
import org.springframework.http.ResponseEntity;

public class RestWorkoutPresenter implements WorkoutPresenter {
    private ResponseEntity<WorkoutViewModel> response;

    @Override
    public void present(WorkoutDocument workoutDocument) {
        WorkoutOutput output = new WorkoutOutput(workoutDocument);
        this.response = ResponseEntity.ok(output.toViewModel());
    }

    public ResponseEntity<WorkoutViewModel> getResponse() {
        return response;
    }
}
