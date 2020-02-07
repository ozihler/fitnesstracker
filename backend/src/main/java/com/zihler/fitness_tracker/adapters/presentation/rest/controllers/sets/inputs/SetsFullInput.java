package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullSetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WaitingTime;
import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.UnitOfTime;
import com.zihler.fitness_tracker.domain.values.Weight;

import java.util.List;

import static com.zihler.fitness_tracker.domain.values.UnitOfMeasurement.KILOGRAMM;
import static java.util.stream.Collectors.toList;

public class SetsFullInput {
    private List<FullSetViewModel> sets;

    public SetsFullInput(List<FullSetViewModel> sets) {
        this.sets = sets;
    }

    public SetsDocument toDocument() {
        return SetsDocument.of(sets.stream()
                .map(this::toDocument)
                .collect(toList()));
    }

    private SetDocument toDocument(FullSetViewModel set) {
        return SetDocument.of(
                Weight.of(set.getWeight(), KILOGRAMM),
                Repetitions.of(set.getNumberOfRepetitions()),
                WaitingTime.of(set.getWaitingTime(), UnitOfTime.SECONDS)
        );
    }
}
