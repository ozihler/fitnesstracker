package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToCreateRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestWorkoutPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestWorkoutsInOverviewPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsInOverviewViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkoutsResource {
    private static final Logger logger = LogManager.getLogger();

    private CreateWorkoutController createWorkoutController;
    private UpdateWorkoutController updateWorkoutController;
    private ViewAllWorkoutsController viewAllWorkoutsController;

    @Autowired
    public WorkoutsResource(
            CreateWorkoutController createWorkoutController,
            UpdateWorkoutController updateWorkoutController,
            ViewAllWorkoutsController viewAllWorkoutsController) {
        this.createWorkoutController = createWorkoutController;
        this.updateWorkoutController = updateWorkoutController;
        this.viewAllWorkoutsController = viewAllWorkoutsController;
    }

    @PostMapping(path = "/api/workouts")
    public ResponseEntity<WorkoutViewModel> createWorkout(@RequestBody WorkoutToCreateRequest request) {
        RestWorkoutPresenter output = new RestWorkoutPresenter();

        this.createWorkoutController.createWorkout(request, output);
        logger.debug("Created workout {}", output.getResponse().getBody());

        return output.getResponse();
    }

    @PutMapping(path = "/api/workouts")
    public ResponseEntity<WorkoutViewModel> updateWorkout(@RequestBody WorkoutViewModel request) {
        RestWorkoutPresenter output = new RestWorkoutPresenter();

        this.updateWorkoutController.updateWorkout(request, output);
        logger.debug("Updated workout {}", output.getResponse().getBody());

        return output.getResponse();
    }

    @GetMapping(path = "/api/workouts")
    public ResponseEntity<WorkoutsInOverviewViewModel> fetchAll() {
        RestWorkoutsInOverviewPresenter output = new RestWorkoutsInOverviewPresenter();

        this.viewAllWorkoutsController.viewAllWorkouts(output);
        logger.debug("All workouts:{}", output.getResponse());

        return output.getResponse();
    }
}
