package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;

public class SetOutput {
    private SetDocument set;

    public SetOutput(SetDocument set) {
        this.set = set;
    }


    public SetViewModel toViewModel() {
        return new SetViewModel(
                0,
                set.getWeight().value(),
                set.getWeight().unitOfMeasurement().shortname(),
                set.getRepetitions().number(),
                set.getWaitingTime().value(),
                set.getWaitingTime().unitOfTime().shortname()
        );
    }
}
