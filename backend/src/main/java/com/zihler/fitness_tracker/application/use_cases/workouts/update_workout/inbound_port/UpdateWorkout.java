package com.zihler.fitness_tracker.application.use_cases.workouts.update_workout.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;

public interface UpdateWorkout {
    void invokeWith(WorkoutDocument workout, WorkoutPresenter output);
}
