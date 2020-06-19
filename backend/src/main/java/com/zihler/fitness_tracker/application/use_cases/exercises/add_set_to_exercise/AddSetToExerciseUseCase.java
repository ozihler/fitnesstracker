package com.zihler.fitness_tracker.application.use_cases.exercises.add_set_to_exercise;

import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.AddSetToWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.SetPresenter;
import com.zihler.fitness_tracker.application.use_cases.exercises.add_set_to_exercise.inbound_port.AddSetToExercise;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class AddSetToExerciseUseCase implements AddSetToExercise {
    private final AddSetToWorkout addSetToWorkout;

    public AddSetToExerciseUseCase(AddSetToWorkout addSetToWorkout) {
        this.addSetToWorkout = addSetToWorkout;
    }

    @Override
    public void invokeWith(WorkoutId workoutId, Name exerciseName, SetDocument set, SetPresenter output) {
        Set setToCreate = Set.withValues(set.getWeight(), set.getRepetitions(), set.getWaitingTime());
        Set storedSet = addSetToWorkout.withValues(workoutId, exerciseName, setToCreate);
        output.present(SetDocument.of(storedSet));
    }
}
