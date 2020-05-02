package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.WorkoutIdOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.CopiedWorkoutPresenter;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import org.springframework.http.ResponseEntity;

public class WorkoutIdPresenter extends RestPresenter<WorkoutViewModel> implements CopiedWorkoutPresenter {
    @Override
    public void present(WorkoutId workoutId) {
        WorkoutIdOutput output = new WorkoutIdOutput(workoutId);

        this.response = ResponseEntity.ok(output.toViewModel());
    }

}
