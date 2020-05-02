package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles;

import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.ExerciseInput;
import com.zihler.fitness_tracker.domain.values.Multiplier;
import com.zihler.fitness_tracker.domain.values.Name;

public class ExerciseToCopy {
    private Exercise self;

    public ExerciseToCopy(Exercise self) {
        this.self = self;
    }

    public Exercise copy() {
        return ExerciseInput.of(
                Name.of(self.getName().toString()), new SetsToCopy(self.getSets()).copy(), Multiplier.defaultValue());
    }
}
