package com.zihler.fitness_tracker.application.use_cases.exercises.add_set_to_exercise.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.SetPresenter;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public interface AddSetToExercise {
    void invokeWith(WorkoutId workoutId, Name exerciseName, SetDocument set, SetPresenter output);
}
