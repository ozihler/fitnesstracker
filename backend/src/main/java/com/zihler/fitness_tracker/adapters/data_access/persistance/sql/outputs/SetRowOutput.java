package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading.SetJson;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.SetRow;

public class SetRowOutput {
    private final SetJson json;

    public SetRowOutput(SetJson json) {
        this.json = json;
    }


    public SetRow getRow() {
        var setRow = new SetRow();

        setRow.setWeight(json.getWeight());
        setRow.setWeightUnit(json.getWeightUnit());
        setRow.setRepetitions(json.getNumberOfRepetitions());
        setRow.setWaitingTime(json.getWaitingTime());
        setRow.setWaitingTimeUnit(json.getWaitingTimeUnit());

        return setRow;
    }
}
