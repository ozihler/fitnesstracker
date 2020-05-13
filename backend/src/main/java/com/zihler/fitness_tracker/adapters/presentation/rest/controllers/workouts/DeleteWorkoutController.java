package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestFullWorkoutPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.use_cases.workouts.delete_workout.DeleteWorkout;
import com.zihler.fitness_tracker.application.use_cases.workouts.delete_workout.DeleteWorkoutUseCase;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteWorkoutController {

    private DeleteWorkout deleteWorkout;

    @Autowired
    public DeleteWorkoutController(FetchWorkout fetchWorkout, StoreWorkout storeWorkout) {
        this.deleteWorkout = new DeleteWorkoutUseCase(fetchWorkout, storeWorkout);
    }

    public ResponseEntity<WorkoutViewModel> deleteWorkout(String workoutId) {
        var input = WorkoutId.of(workoutId);
        var output = new RestFullWorkoutPresenter();

        this.deleteWorkout.withId(input, output);

        return output.getResponse();
    }
}
