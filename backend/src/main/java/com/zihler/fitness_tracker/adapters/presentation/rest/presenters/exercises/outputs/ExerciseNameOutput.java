package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseNameViewModel;
import com.zihler.fitness_tracker.domain.values.ExerciseName;
import org.springframework.http.ResponseEntity;

public class ExerciseNameOutput {
    private ExerciseName name;

    public ExerciseNameOutput(ExerciseName name) {
        this.name = name;
    }

    public ResponseEntity<ExerciseNameViewModel> toViewModel() {
        return ResponseEntity.ok(new ExerciseNameViewModel(name.toString()));
    }
}
