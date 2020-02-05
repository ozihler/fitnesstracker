package com.zihler.fitness_tracker.application.use_cases.view_single_workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.view_single_workout.inbound_port.ViewSingleWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class ViewSingleWorkoutUseCase implements ViewSingleWorkout {
    private FetchWorkout fetchWorkout;

    public ViewSingleWorkoutUseCase(FetchWorkout fetchWorkout) {
        this.fetchWorkout = fetchWorkout;
    }

    @Override
    public void invokeWith(WorkoutId workoutWorkoutId, WorkoutPresenter output) {
        Workout workout = fetchWorkout.by(workoutWorkoutId);
        output.present(WorkoutDocument.of(workout));
    }
}
