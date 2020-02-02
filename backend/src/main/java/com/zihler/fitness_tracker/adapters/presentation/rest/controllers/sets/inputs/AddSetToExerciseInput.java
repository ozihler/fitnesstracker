package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.exceptions.IllegalSetDetailsException;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetDetails;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WaitingTime;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.Weight;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

public class AddSetToExerciseInput {
    private final String exerciseName;
    private final SetDetails setDetails;

    public AddSetToExerciseInput(String exerciseName, SetDetails setDetails) {
        this.exerciseName = exerciseName;
        this.setDetails = setDetails;
        System.out.printf("Received AddSetToExercise Request with Exercise name: %s, Set Details: %s%n", exerciseName, setDetails);
    }

    public SetDocument set() {
        if (Strings.isBlank(setDetails.getSetDetails())) {
            throw new IllegalSetDetailsException("Set cannot be null!");
        }

        String[] parts = setDetails.getSetDetails().split("_");
        if (parts.length < 2) {
            throw new IllegalSetDetailsException("Sets need to have at least weight and repetitions set! Received: " + Arrays.toString(parts));
        }

        return SetDocument.of(
                weight(parts[0]),
                repetitions(parts[1]),
                waitingTime(parts));
    }

    private Weight weight(String part) {
        float weightValue = Float.parseFloat(part);
        return Weight.of(weightValue);
    }

    private Repetitions repetitions(String part) {
        return Repetitions.of(Integer.parseInt(part));
    }

    private WaitingTime waitingTime(String[] parts) {
        WaitingTime waitingTime = WaitingTime.empty();
        if (parts.length == 3) {
            waitingTime = WaitingTime.of(Integer.parseInt(parts[2]));
        }
        return waitingTime;
    }

    public Name exerciseName() {
        return Name.of(exerciseName);
    }
}
