package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.*;

import java.util.List;
import java.util.stream.Collectors;

public class SetFilesInput {
    private List<SetViewModel> sets;

    public SetFilesInput(List<SetViewModel> sets) {
        this.sets = sets;
    }

    public Sets sets() {
        return Sets.of(sets.stream()
                .map(s -> Set.withValues(
                        Weight.of(s.getWeight(), UnitOfMeasurement.fromShortName(s.getWeightUnit())),
                        Repetitions.of(s.getNumberOfRepetitions()),
                        WaitingTime.of(s.getWaitingTime(), UnitOfTime.fromShortName(s.getWaitingTimeUnit()))))
                .collect(Collectors.toList()));
    }

}
