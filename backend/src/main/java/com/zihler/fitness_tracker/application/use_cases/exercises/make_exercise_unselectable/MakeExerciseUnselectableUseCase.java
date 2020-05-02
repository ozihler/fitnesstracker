package com.zihler.fitness_tracker.application.use_cases.exercises.make_exercise_unselectable;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs.ExerciseNameInput;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercise;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreExercise;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExerciseNamePresenter;
import com.zihler.fitness_tracker.application.use_cases.exercises.make_exercise_unselectable.inbound_port.MakeExerciseUnselectable;
import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.Name;

public class MakeExerciseUnselectableUseCase implements MakeExerciseUnselectable {
    private FetchExercise fetchExercise;
    private StoreExercise storeExercise;

    public MakeExerciseUnselectableUseCase(FetchExercise fetchExercise, StoreExercise storeExercise) {
        this.fetchExercise = fetchExercise;
        this.storeExercise = storeExercise;
    }

    @Override
    public void invokeWith(ExerciseNameInput input, ExerciseNamePresenter output) {
        Name exerciseName = input.exerciseName();
        Exercise exercise = fetchExercise.byName(exerciseName);
        exercise.setSelectable(false);
        Exercise storedExercise = storeExercise.withValues(exercise);
        output.present(Name.of(storedExercise.getName().toString()));
    }
}
