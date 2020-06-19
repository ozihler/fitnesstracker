package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.zihler.fitness_tracker.domain.values.Sets;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SetsFromJsonInput {
    private final List<SetJson> jsons;

    public SetsFromJsonInput(List<SetJson> jsons) {
        this.jsons = jsons;
    }

    public Sets getSets() {
        return Sets.of(
                jsons.stream()
                        .map(SetFromJsonInput::new)
                        .map(SetFromJsonInput::getSet)
                        .collect(toList())
        );
    }
}
