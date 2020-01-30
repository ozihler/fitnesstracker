package com.zihler.fitness_tracker.application.use_cases.create_exercises.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;

public interface CreateExercises {
    void forMuscleGroup(MuscleGroupDocument muscleGroup, ExercisesDocument exercises, ExercisesPresenter output);
}
