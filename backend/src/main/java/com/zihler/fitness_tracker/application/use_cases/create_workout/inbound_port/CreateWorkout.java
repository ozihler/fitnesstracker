package com.zihler.fitness_tracker.application.use_cases.create_workout.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

public interface CreateWorkout {
    void invokeWith(WorkoutTitle workoutTitle, WorkoutPresenter output);
}
