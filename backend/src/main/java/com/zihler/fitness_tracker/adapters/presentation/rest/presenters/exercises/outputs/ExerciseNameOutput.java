package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.domain.values.Name;
import org.springframework.http.ResponseEntity;

public class ExerciseNameOutput {
    private Name name;

    public ExerciseNameOutput(Name name) {
        this.name = name;
    }

    public ResponseEntity<ExerciseViewModel> toViewModel() {
        return ResponseEntity.ok(new ExerciseViewModel(name.toString()));
    }
}
