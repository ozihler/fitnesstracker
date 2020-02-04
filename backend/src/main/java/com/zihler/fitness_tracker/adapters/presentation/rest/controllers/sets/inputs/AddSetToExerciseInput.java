package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.exceptions.IllegalSetDetailsException;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.requests.SetDetails;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WaitingTime;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.Weight;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

public class AddSetToExerciseInput {
    private final String exerciseName;
    private final SetDetails setDetails;

    public AddSetToExerciseInput(String exerciseName, SetDetails setDetails) {
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
        return Weight.of(weightValue);
    }

    private Repetitions repetitionsFrom(String part) {
        return Repetitions.of(Integer.parseInt(part));
    }

    private WaitingTime waitingTimeFrom(List<String> parts) {
        WaitingTime waitingTime = WaitingTime.empty();
        if (parts.size() >= 3) {
            waitingTime = WaitingTime.of(Integer.parseInt(parts.get(2)));
        }
        return waitingTime;
    }

    public Name exerciseName() {
        return Name.of(exerciseName);
    }
}
