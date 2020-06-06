package com.zihler.fitness_tracker.domain.values;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Names {
    private final List<Name> names;

    private Names(List<Name> names) {
        this.names = names;
    }

    public static Names in(String namesString) {
        String[] names = namesString.trim().split("[;,.]+");

        return new Names(Arrays.stream(names)
                .map(String::trim)
                .map(Name::of)
                .collect(toList()));
    }

    public List<Name> values() {
        return names;
    }

}
