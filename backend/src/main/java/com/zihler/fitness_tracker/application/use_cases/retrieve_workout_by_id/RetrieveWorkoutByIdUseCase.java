package com.zihler.fitness_tracker.application.use_cases.retrieve_workout_by_id;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.retrieve_workout_by_id.inbound_port.RetrieveWorkoutById;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.GID;

public class RetrieveWorkoutByIdUseCase implements RetrieveWorkoutById {
    private FetchWorkout fetchWorkout;

    public RetrieveWorkoutByIdUseCase(FetchWorkout fetchWorkout) {
        this.fetchWorkout = fetchWorkout;
    }

    @Override
    public void invokeWith(GID workoutGid, WorkoutPresenter output) {
        Workout workout = fetchWorkout.by(workoutGid);
        output.present(WorkoutDocument.of(workout));
    }
}
