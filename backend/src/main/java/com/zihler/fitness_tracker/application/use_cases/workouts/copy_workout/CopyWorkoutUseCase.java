package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.GenerateWorkoutId;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.CopiedWorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.inbound_port.CopyWorkout;
import com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles.WorkoutToCopy;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class CopyWorkoutUseCase implements CopyWorkout {
    private final FetchWorkout fetchWorkout;
    private final StoreWorkout storeWorkout;
    private final GenerateWorkoutId generateWorkoutId;

    public CopyWorkoutUseCase(FetchWorkout fetchWorkout, StoreWorkout storeWorkout, GenerateWorkoutId generateWorkoutId) {
        this.fetchWorkout = fetchWorkout;
        this.storeWorkout = storeWorkout;
        this.generateWorkoutId = generateWorkoutId;
    }

    @Override
    public void withId(WorkoutId workoutId, CopiedWorkoutPresenter output) {
        Workout targetToCopy = fetchWorkout.by(workoutId);

        WorkoutToCopy workoutToCopy = new WorkoutToCopy(targetToCopy, storeWorkout, generateWorkoutId, output);

        workoutToCopy.makeCopy()
                .presentCopiedWorkoutId();
    }
}
