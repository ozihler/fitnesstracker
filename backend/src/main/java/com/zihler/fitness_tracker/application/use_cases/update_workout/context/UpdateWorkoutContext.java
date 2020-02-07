package com.zihler.fitness_tracker.application.use_cases.update_workout.context;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.update_workout.roles.UpdatableWorkout;

public class UpdateWorkoutContext implements com.zihler.fitness_tracker.application.use_cases.UseCaseContext {
    private final WorkoutDocument updatedWorkout;
    private final UpdatableWorkout updatableWorkout;

    private UpdateWorkoutContext(FetchWorkout fetchWorkout, StoreWorkout storeWorkout, WorkoutDocument updatedWorkout, WorkoutPresenter output) {
        this.updatedWorkout = updatedWorkout;
        var workoutToUpdate = fetchWorkout.by(updatedWorkout.getWorkoutId());
        updatableWorkout = new UpdatableWorkout(workoutToUpdate, storeWorkout, output);
    }

    public static UpdateWorkoutContext initialize(FetchWorkout fetchWorkout, StoreWorkout storeWorkout, WorkoutDocument update, WorkoutPresenter output) {
        return new UpdateWorkoutContext(fetchWorkout, storeWorkout, update, output);
    }

    @Override
    public void enactUseCase() {
        updatableWorkout.updateWith(updatedWorkout);
        updatableWorkout.store();
        updatableWorkout.present();
    }
}
