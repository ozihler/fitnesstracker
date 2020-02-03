package com.zihler.fitness_tracker.domain.values;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Names {
    private Set<Name> names;

    public Names(Set<Name> names) {
        this.names = names;
    }

    public static Names in(String namesString) {
        String[] names = namesString.split("[;,.]+");

        return new Names(Arrays.stream(names)
                .map(String::trim)
                .map(Name::of)
                .collect(toSet()));
    }

    public Set<Name> values() {
        return names;
    }
}
