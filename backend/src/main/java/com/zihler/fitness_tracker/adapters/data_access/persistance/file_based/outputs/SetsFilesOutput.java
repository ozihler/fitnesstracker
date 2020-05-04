package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.domain.values.Sets;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SetsFilesOutput {
    private Sets sets;

    public SetsFilesOutput(Sets sets) {
        this.sets = sets;
    }

    public List<SetViewModel> files() {
        return sets.getSets().stream()
                .map(s -> new SetViewModel(
                        s.getWeight().value(),
                        s.getWeight().unitOfMeasurement().shortname(),
                        s.getRepetitions().number(),
                        s.getWaitingTime().value(),
                        s.getWaitingTime().unitOfTime().shortname()))
                .collect(toList());
    }
}
