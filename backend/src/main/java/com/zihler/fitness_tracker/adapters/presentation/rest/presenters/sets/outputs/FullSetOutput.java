package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullSetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;

public class FullSetOutput {
    private SetDocument set;

    public FullSetOutput(SetDocument set) {
        this.set = set;
    }


    public FullSetViewModel toViewModel() {
        return new FullSetViewModel(
                set.getWeight().value(),
                set.getWeight().unitOfMeasurement().shortname(),
                set.getRepetitions().number(),
                set.getWaitingTime().value(),
                set.getWaitingTime().unitOfTime().shortname()
        );
    }
}
