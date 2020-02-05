package com.zihler.fitness_tracker.application.use_cases.create_workout;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.create_workout.inbound_port.CreateWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

public class CreateWorkoutUseCase implements CreateWorkout {
    private StoreWorkout workouts;

    public CreateWorkoutUseCase(StoreWorkout workouts) {
        this.workouts = workouts;
    }

    @Override
    public void invokeWith(WorkoutTitle workoutTitle, WorkoutPresenter output) {
        Workout workout = Workout.newWorkout(workoutTitle);
        Workout stored = workouts.as(workout);
        output.present(WorkoutDocument.of(stored));
    }
}
