package com.zihler.fitness_tracker.application.use_cases.workouts.delete_workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestFullWorkoutPresenter;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class DeleteWorkoutUseCase implements DeleteWorkout {
    private FetchWorkout fetchWorkout;
    private StoreWorkout storeWorkout;

    public DeleteWorkoutUseCase(FetchWorkout fetchWorkout, StoreWorkout storeWorkout) {
        this.fetchWorkout = fetchWorkout;
        this.storeWorkout = storeWorkout;
    }

    @Override
    public void withId(WorkoutId input, RestFullWorkoutPresenter output) {
        Workout workoutToDelete = this.fetchWorkout.by(input);

        workoutToDelete.setDeleted(true);

        Workout deletedWorkout = this.storeWorkout.withValues(workoutToDelete);

        output.present(WorkoutDocument.of(deletedWorkout));
    }
}
