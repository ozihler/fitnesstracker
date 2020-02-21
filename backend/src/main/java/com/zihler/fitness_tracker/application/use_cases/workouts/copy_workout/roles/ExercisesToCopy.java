package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles;

import com.zihler.fitness_tracker.domain.values.Exercises;

import static java.util.stream.Collectors.toList;

public class ExercisesToCopy {
    private Exercises self;

    public ExercisesToCopy(Exercises self) {
        this.self = self;
    }

    public Exercises copy() {
        return Exercises.of(
                self.getExercises().stream()
                        .map(ExerciseToCopy::new)
                        .map(ExerciseToCopy::copy)
                        .collect(toList()));
    }
}
