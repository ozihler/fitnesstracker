package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles;

import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import static java.util.stream.Collectors.toList;

public class MuscleGroupsToCopy {
    private MuscleGroups self;

    public MuscleGroupsToCopy(MuscleGroups self) {
        this.self = self;
    }

    public MuscleGroups copy() {
        return MuscleGroups.of(self.getMuscleGroups()
                .stream()
                .map(MuscleGroupToCopy::new)
                .map(MuscleGroupToCopy::copy)
                .collect(toList()));
    }
}
