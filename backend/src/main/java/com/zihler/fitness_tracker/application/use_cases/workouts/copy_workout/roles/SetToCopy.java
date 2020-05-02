package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles;

import com.zihler.fitness_tracker.domain.values.WaitingTime;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.Weight;

public class SetToCopy {
    private Set self;

    public SetToCopy(Set self) {
        this.self = self;
    }

    public Set copy() {
        return Set.withValues(
                Weight.of(self.getWeight().value(), self.getWeight().unitOfMeasurement()),
                Repetitions.of(self.getRepetitions().number()),
                WaitingTime.of(self.getWaitingTime().value(), self.getWaitingTime().unitOfTime())
        );
    }
}
