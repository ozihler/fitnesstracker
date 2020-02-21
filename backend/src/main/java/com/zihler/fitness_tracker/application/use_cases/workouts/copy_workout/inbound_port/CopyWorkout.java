package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.presenters.CopiedWorkoutPresenter;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public interface CopyWorkout {
    void withId(WorkoutId workoutId, CopiedWorkoutPresenter output);
}
