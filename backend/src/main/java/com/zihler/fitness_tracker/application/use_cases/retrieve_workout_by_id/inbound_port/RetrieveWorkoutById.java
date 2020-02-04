package com.zihler.fitness_tracker.application.use_cases.retrieve_workout_by_id.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.domain.values.GID;

public interface RetrieveWorkoutById {
    void invokeWith(GID workoutGid, WorkoutPresenter output);
}
