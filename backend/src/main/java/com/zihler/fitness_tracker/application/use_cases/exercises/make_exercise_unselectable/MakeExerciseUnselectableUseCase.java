package com.zihler.fitness_tracker.application.use_cases.exercises.make_exercise_unselectable;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs.ExerciseNameInput;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercise;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.UpdateExistingExercise;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExerciseNamePresenter;
import com.zihler.fitness_tracker.application.use_cases.exercises.make_exercise_unselectable.inbound_port.MakeExerciseUnselectable;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.values.Name;

public class MakeExerciseUnselectableUseCase implements MakeExerciseUnselectable {
    private final FetchExercise fetchExercise;
    private final UpdateExistingExercise updateExistingExercise;

    public MakeExerciseUnselectableUseCase(FetchExercise fetchExercise, UpdateExistingExercise updateExistingExercise) {
        this.fetchExercise = fetchExercise;
        this.updateExistingExercise = updateExistingExercise;
    }

    @Override
    public void invokeWith(ExerciseNameInput input, ExerciseNamePresenter output) {
        Name exerciseName = input.exerciseName();
        Exercise exercise = fetchExercise.byName(exerciseName);
        exercise.setSelectable(false);
        Exercise storedExercise = updateExistingExercise.withValues(exercise);
        output.present(Name.of(storedExercise.getName().toString()));
    }
}
