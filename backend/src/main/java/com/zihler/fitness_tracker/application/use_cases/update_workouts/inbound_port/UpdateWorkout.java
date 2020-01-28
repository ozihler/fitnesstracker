package com.zihler.fitness_tracker.application.use_cases.update_workouts.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;

public interface UpdateWorkout {
    void callWith(WorkoutDocument workout, WorkoutPresenter output);
}
