package com.zihler.fitness_tracker.application.use_cases.exercises.make_exercise_unselectable.inbound_port;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs.ExerciseNameInput;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExerciseNamePresenter;

public interface MakeExerciseUnselectable {
    void invokeWith(ExerciseNameInput input, ExerciseNamePresenter output);
}
