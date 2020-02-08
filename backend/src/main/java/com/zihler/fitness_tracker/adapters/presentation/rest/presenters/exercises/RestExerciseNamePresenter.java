package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs.ExerciseNameOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseNameViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExerciseNamePresenter;
import com.zihler.fitness_tracker.domain.values.ExerciseName;

public class RestExerciseNamePresenter
        extends RestPresenter<ExerciseNameViewModel>
        implements ExerciseNamePresenter {

    @Override
    public void present(ExerciseName exerciseName) {
        var output = new ExerciseNameOutput(exerciseName);

        response = output.toViewModel();
    }
}
