package com.zihler.fitness_tracker.application.use_cases.view_all_exercises_for_muscle_group.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;

public interface ViewAllExercisesForMuscleGroup {
    void invokeWith(MuscleGroupDocument muscleGroup, ExercisesPresenter output);
}
