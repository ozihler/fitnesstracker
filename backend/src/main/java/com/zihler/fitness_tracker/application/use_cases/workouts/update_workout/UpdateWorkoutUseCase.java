package com.zihler.fitness_tracker.application.use_cases.workouts.update_workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.workouts.update_workout.context.UpdateWorkoutContext;
import com.zihler.fitness_tracker.application.use_cases.workouts.update_workout.inbound_port.UpdateWorkout;

public class UpdateWorkoutUseCase implements UpdateWorkout {
    private FetchWorkout fetchWorkout;
    private StoreWorkout storeWorkout;

    public UpdateWorkoutUseCase(FetchWorkout fetchWorkout, StoreWorkout storeWorkout) {
        this.fetchWorkout = fetchWorkout;
        this.storeWorkout = storeWorkout;
    }

    @Override
    public void invokeWith(WorkoutDocument update, WorkoutPresenter output) {
        UpdateWorkoutContext.initialize(fetchWorkout, storeWorkout, update, output)
                .enactUseCase();
    }


}
