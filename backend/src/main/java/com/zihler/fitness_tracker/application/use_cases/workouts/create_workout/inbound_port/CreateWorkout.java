package com.zihler.fitness_tracker.application.use_cases.workouts.create_workout.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;

public interface CreateWorkout {
    void invokeWith( WorkoutPresenter output);
}
