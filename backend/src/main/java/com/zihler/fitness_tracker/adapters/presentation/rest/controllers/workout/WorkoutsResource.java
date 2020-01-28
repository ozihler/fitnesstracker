package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestWorkoutPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkoutsResource {
    private CreateWorkoutController createWorkoutController;
    private UpdateWorkoutController updateWorkoutController;

    @Autowired
    public WorkoutsResource(CreateWorkoutController createWorkoutController, UpdateWorkoutController updateWorkoutController) {
        this.createWorkoutController = createWorkoutController;
        this.updateWorkoutController = updateWorkoutController;
    }

    @PostMapping(path = "/workouts")
    public ResponseEntity<WorkoutViewModel> createWorkout(@RequestBody WorkoutToCreateRequest request) {
        RestWorkoutPresenter output = new RestWorkoutPresenter();

        this.createWorkoutController.createWorkout(request, output);

        return output.getResponse();
    }

    @PutMapping(path = "/workouts")
    public ResponseEntity<WorkoutViewModel> updateWorkout(@RequestBody WorkoutViewModel request) {
        RestWorkoutPresenter output = new RestWorkoutPresenter();

        this.updateWorkoutController.updateWorkout(request, output);

        return output.getResponse();
    }
}
