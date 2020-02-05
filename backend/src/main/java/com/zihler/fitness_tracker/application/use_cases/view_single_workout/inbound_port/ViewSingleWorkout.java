package com.zihler.fitness_tracker.application.use_cases.view_single_workout.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public interface ViewSingleWorkout {
    void invokeWith(WorkoutId workoutWorkoutId, WorkoutPresenter output);
}
