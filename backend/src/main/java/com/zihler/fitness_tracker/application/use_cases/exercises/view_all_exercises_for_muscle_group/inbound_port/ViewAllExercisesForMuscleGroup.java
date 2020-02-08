package com.zihler.fitness_tracker.application.use_cases.exercises.view_all_exercises_for_muscle_group.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.domain.values.MuscleGroupName;

public interface ViewAllExercisesForMuscleGroup {
    void invokeWith(MuscleGroupName muscleGroupName, ExercisesPresenter output);
}
