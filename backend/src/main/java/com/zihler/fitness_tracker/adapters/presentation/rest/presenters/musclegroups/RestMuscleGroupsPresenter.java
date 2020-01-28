package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;
import org.springframework.http.ResponseEntity;

public class RestMuscleGroupsPresenter implements MuscleGroupsPresenter {
    private ResponseEntity<MuscleGroupsViewModel> response;

    @Override
    public void present(MuscleGroupsDocument muscleGroups) {
        var output = new MuscleGroupsOutput(muscleGroups);
        this.response = ResponseEntity.ok(output.toViewModel());
    }

    public ResponseEntity<MuscleGroupsViewModel> getResponse() {
        return this.response;
    }
}
