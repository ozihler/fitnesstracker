package com.zihler.fitness_tracker.application.use_cases.workouts.delete_workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestFullWorkoutPresenter;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public interface DeleteWorkout {
    void withId(WorkoutId input, RestFullWorkoutPresenter output);
}
