package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupNameViewModel;
import com.zihler.fitness_tracker.domain.values.Name;
import org.springframework.http.ResponseEntity;

public class MuscleGroupNameOutput {
    private Name name; // todo make MuscleGroupName

    public MuscleGroupNameOutput(Name name) {
        this.name = name;
    }

    public ResponseEntity<MuscleGroupNameViewModel> toViewModel() {
        return ResponseEntity.ok(new MuscleGroupNameViewModel(name.toString()));
    }
}
