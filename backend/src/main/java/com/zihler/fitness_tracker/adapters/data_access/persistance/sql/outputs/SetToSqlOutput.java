package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.SetRow;
import com.zihler.fitness_tracker.domain.entities.Set;

public class SetToSqlOutput {
    private final Set set;

    public SetToSqlOutput(Set set) {
        this.set = set;
    }

    public SetRow getRow() {
        var row = new SetRow();

        row.setWeight(set.getWeight().value());
        row.setWeightUnit(set.getWeight().unitOfMeasurement().shortname());
        row.setRepetitions(set.getRepetitions().number());
        row.setWaitingTime(set.getWaitingTime().value());
        row.setWaitingTimeUnit(set.getWaitingTime().unitOfTime().shortname());

        return row;
    }
}
