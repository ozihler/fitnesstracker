package com.zihler.fitness_tracker.application.use_cases.create_exercises.inbound_port;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests.ExercisesToCreate;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;

public interface CreateExercises {
    void forMuscleGroup(ExercisesToCreate exercisesToCreate, ExercisesPresenter output);
}
