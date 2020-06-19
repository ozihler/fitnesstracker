package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.SetRow;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.*;

public class SetFromSqlInput {
    private final SetRow setRow;

    public SetFromSqlInput(SetRow setRow) {
        this.setRow = setRow;
    }

    public Set getSet() {
        return new Set(
                Weight.of(setRow.getWeight(), UnitOfMeasurement.valueOf(setRow.getWeightUnit())),
                Repetitions.of(setRow.getRepetitions()),
                WaitingTime.of(setRow.getWaitingTime(), UnitOfTime.valueOf(setRow.getWaitingTimeUnit()))
        );
    }
}
