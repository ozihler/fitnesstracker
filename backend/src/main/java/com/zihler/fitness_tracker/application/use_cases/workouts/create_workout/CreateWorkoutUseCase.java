package com.zihler.fitness_tracker.application.use_cases.workouts.create_workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.GenerateWorkoutId;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.workouts.create_workout.inbound_port.CreateWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

public class CreateWorkoutUseCase implements CreateWorkout {
    private StoreWorkout workouts;
    private GenerateWorkoutId generateWorkoutId;


    public CreateWorkoutUseCase(StoreWorkout workouts, GenerateWorkoutId generateWorkoutId) {
        this.workouts = workouts;
        this.generateWorkoutId = generateWorkoutId;
    }

    @Override
    public void invokeWith(WorkoutTitle workoutTitle, WorkoutPresenter output) {
        Workout workout = Workout.newWorkout(workoutTitle, generateWorkoutId.next());
        Workout stored = workouts.withValues(workout);
        output.present(WorkoutDocument.of(stored));
    }
}
