package com.zihler.fitness_tracker.application.use_cases.add_set_to_exercise.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.SetPresenter;
import com.zihler.fitness_tracker.domain.values.Name;

public interface AddSetToExercise {
    void invokeWith(Name exerciseName, SetDocument set, SetPresenter output);
}
