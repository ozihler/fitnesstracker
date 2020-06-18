package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading.ExerciseJson;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;

public class ExerciseRowOutput {
    private final ExerciseJson json;

    public ExerciseRowOutput(ExerciseJson json) {
        this.json = json;
    }

    public ExerciseRow getRow() {
        var row = new ExerciseRow();
        row.setMultiplier(json.getMultiplier());
        row.setName(json.getName());
        row.setSelectable(json.isSelectable());
        row.setSets(new SetsRowOutput(json.getSets()).getRows());

        return row;
    }
}
