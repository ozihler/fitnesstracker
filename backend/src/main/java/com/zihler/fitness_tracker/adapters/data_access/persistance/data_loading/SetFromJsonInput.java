package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.*;

public class SetFromJsonInput {
    private final SetJson json;

    public SetFromJsonInput(SetJson json) {
        this.json = json;
    }

    public Set getSet() {
        return new Set(
                Weight.of(json.getWeight(), UnitOfMeasurement.fromShortName(json.getWeightUnit())),
                Repetitions.of(json.getNumberOfRepetitions()),
                WaitingTime.of(json.getWaitingTime(), UnitOfTime.fromShortName(json.getWaitingTimeUnit()))
        );
    }

}
