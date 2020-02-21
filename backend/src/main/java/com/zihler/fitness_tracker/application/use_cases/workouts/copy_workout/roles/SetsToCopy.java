package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles;

import com.zihler.fitness_tracker.domain.values.Sets;

import static java.util.stream.Collectors.toList;

public class SetsToCopy {
    private Sets sets;

    public SetsToCopy(Sets sets) {
        this.sets = sets;
    }

    public Sets copy() {
        return Sets.of(sets.getSets()
                .stream()
                .map(SetToCopy::new)
                .map(SetToCopy::copy)
                .collect(toList()));
    }
}
