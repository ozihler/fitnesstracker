package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestWorkoutPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkoutsResource {
    private CreateWorkoutController createWorkoutController;

    @Autowired
    public WorkoutsResource(CreateWorkoutController createWorkoutController) {
        this.createWorkoutController = createWorkoutController;
    }

    @PostMapping(path="/workouts")
    public ResponseEntity<WorkoutViewModel> createWorkout(@RequestBody WorkoutToCreateRequest request) {
        RestWorkoutPresenter output = new RestWorkoutPresenter();

        this.createWorkoutController.createWorkout(request, output);

        return output.getResponse();
    }
}
