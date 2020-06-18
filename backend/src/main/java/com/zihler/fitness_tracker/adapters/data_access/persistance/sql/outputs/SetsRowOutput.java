package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading.SetJson;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.SetRow;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SetsRowOutput {
    private final List<SetJson> jsons;

    public SetsRowOutput(List<SetJson> jsons) {
        this.jsons = jsons;
    }

    public List<SetRow> getRows() {
        return jsons.stream()
                .map(SetRowOutput::new)
                .map(SetRowOutput::getRow)
                .collect(toList());
    }
}
