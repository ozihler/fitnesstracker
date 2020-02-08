package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.exceptions.IllegalSetDetailsException;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.requests.SetDetails;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WaitingTime;
import com.zihler.fitness_tracker.domain.values.*;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

import static com.zihler.fitness_tracker.domain.values.UnitOfMeasurement.KILOGRAMM;

public class AddSetToExerciseInput {
    private final String exerciseName;
    private final SetDetails setDetails;
    private final String workoutId;

    public AddSetToExerciseInput(String workoutId, String exerciseName, SetDetails setDetails) {
        this.workoutId = workoutId;
        this.exerciseName = exerciseName;
        this.setDetails = setDetails;
    }

    public SetDocument set() {
        if (!hasSetDetails()) {
            throw new IllegalSetDetailsException("Set cannot be null!");
        }

        List<String> setDetails = splitSetDetailsIntoParts();

        if (!hasAllRequiredParts(setDetails)) {
            throw new IllegalSetDetailsException("Sets need to have at least weight and repetitions set! Received: " + Strings.join(setDetails, ','));
        }

        return createSetFrom(setDetails);
    }

    private SetDocument createSetFrom(List<String> parts) {
        return SetDocument.of(
                weightFrom(parts.get(0)),
                repetitionsFrom(parts.get(1)),
                waitingTimeFrom(parts));
    }

    private boolean hasAllRequiredParts(List<String> parts) {
        return parts.size() >= 2;
    }

    private List<String> splitSetDetailsIntoParts() {
        return List.of(setDetails.getSetDetails().split("_"));
    }

    private boolean hasSetDetails() {
        return Strings.isNotBlank(setDetails.getSetDetails());
    }

    private Weight weightFrom(String part) {
        float weightValue = Float.parseFloat(part);
        return Weight.of(weightValue, KILOGRAMM);
    }

    private Repetitions repetitionsFrom(String part) {
        return Repetitions.of(Integer.parseInt(part));
    }

    private WaitingTime waitingTimeFrom(List<String> parts) {
        WaitingTime waitingTime = WaitingTime.empty();
        if (parts.size() >= 3) {
            waitingTime = WaitingTime.of(Integer.parseInt(parts.get(2)), UnitOfTime.SECONDS);
        }
        return waitingTime;
    }

    public Name exerciseName() {
        return Name.of(exerciseName);
    }

    public WorkoutId workoutId() {
        return WorkoutId.of(workoutId);
    }
}
