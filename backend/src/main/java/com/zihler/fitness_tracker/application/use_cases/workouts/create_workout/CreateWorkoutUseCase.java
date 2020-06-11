package com.zihler.fitness_tracker.application.use_cases.workouts.create_workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.GenerateWorkoutId;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.workouts.create_workout.inbound_port.CreateWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

public class CreateWorkoutUseCase implements CreateWorkout {
    private final StoreWorkout workouts;
    private final GenerateWorkoutId generateWorkoutId;


    public CreateWorkoutUseCase(StoreWorkout workouts, GenerateWorkoutId generateWorkoutId) {
        this.workouts = workouts;
        this.generateWorkoutId = generateWorkoutId;
    }

    @Override
    public void invokeWith(WorkoutPresenter output) {
        Workout workout = new Workout(generateWorkoutId.next(), CreationDate.now(), new MuscleGroups());
        Workout stored = workouts.withValues(workout);
        output.present(WorkoutDocument.of(stored));
    }
}
