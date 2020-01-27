package com.zihler.fitness_tracker.application.use_cases.create_workout;

import com.zihler.fitness_tracker.application.outbound_ports.IStoreWorkouts;
import com.zihler.fitness_tracker.application.outbound_ports.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.create_workout.inbound_port.CreateWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

public class CreateWorkoutUseCase implements CreateWorkout {
    private IStoreWorkouts workouts;

    public CreateWorkoutUseCase(IStoreWorkouts workouts) {
        this.workouts = workouts;
    }

    @Override
    public void with(WorkoutTitle workoutTitle, WorkoutPresenter output) {
        Workout workout = new Workout(workoutTitle);
        Workout stored = workouts.store(workout);
        output.present(WorkoutDocument.of(stored));
    }
}
