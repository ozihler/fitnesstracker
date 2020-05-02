package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs.ExerciseNameOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExerciseNamePresenter;
import com.zihler.fitness_tracker.domain.values.Name;

public class RestExerciseNamePresenter
        extends RestPresenter<ExerciseViewModel>
        implements ExerciseNamePresenter {

    @Override
    public void present(Name exerciseName) {
        var output = new ExerciseNameOutput(exerciseName);

        response = output.toViewModel();
    }
}
