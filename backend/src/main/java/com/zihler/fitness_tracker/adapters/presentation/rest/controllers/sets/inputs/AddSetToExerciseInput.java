package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.exceptions.IllegalSetDetailsException;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.domain.values.*;

public class AddSetToExerciseInput {
    private final String exerciseName;
    private final SetViewModel setToAdd;
    private final String workoutId;

    public AddSetToExerciseInput(String workoutId, String exerciseName, SetViewModel setToAdd) {
        this.workoutId = workoutId;
        this.exerciseName = exerciseName;
        this.setToAdd = setToAdd;
    }

    public SetDocument set() {
        if (!hasAllRequiredParts(setToAdd)) {
            throw new IllegalSetDetailsException("Sets need to have at least weight and repetitions set! Received: " + setToAdd);
        }

        return SetDocument.of(
                Weight.of(setToAdd.getWeight(), UnitOfMeasurement.fromShortName(setToAdd.getWeightUnit())),
                Repetitions.of(setToAdd.getNumberOfRepetitions()),
                WaitingTime.of(setToAdd.getWaitingTime(), UnitOfTime.fromShortName(setToAdd.getWaitingTimeUnit()))
        );
    }

    private boolean hasAllRequiredParts(SetViewModel parts) {
        return parts.getWeight() > 0 && parts.getNumberOfRepetitions() > 0;
    }

    public Name exerciseName() {
        return Name.of(exerciseName);
    }

    public WorkoutId workoutId() {
        return WorkoutId.of(workoutId);
    }
}
