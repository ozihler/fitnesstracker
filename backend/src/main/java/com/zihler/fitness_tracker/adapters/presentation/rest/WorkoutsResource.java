package com.zihler.fitness_tracker.adapters.presentation.rest;

import com.zihler.fitness_tracker.adapters.presentation.controllers.CreateWorkoutController;
import com.zihler.fitness_tracker.adapters.presentation.rest.json.WorkoutToCreateRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.json.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestWorkoutPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/workouts")
public class WorkoutsResource {
    private CreateWorkoutController createWorkoutController;

    @Autowired
    public WorkoutsResource(CreateWorkoutController createWorkoutController) {
        this.createWorkoutController = createWorkoutController;
    }

    @PostMapping
    public ResponseEntity<WorkoutViewModel> create(@RequestBody WorkoutToCreateRequest request) {
        RestWorkoutPresenter output = new RestWorkoutPresenter();
        this.createWorkoutController.createWorkout(request, output);
        return output.getResponse();
    }
}
