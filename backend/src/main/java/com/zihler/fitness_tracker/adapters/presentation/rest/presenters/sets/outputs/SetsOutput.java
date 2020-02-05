package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.outputs.SetOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetsDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SetsOutput {
    private SetsDocument sets;

    public SetsOutput(SetsDocument sets) {
        this.sets = sets;
    }

    public List<SetViewModel> toViewModel() {
        return sets.getSets()
                .stream()
                .map(SetOutput::new)
                .map(SetOutput::toViewModel)
                .collect(toList());
    }
}
