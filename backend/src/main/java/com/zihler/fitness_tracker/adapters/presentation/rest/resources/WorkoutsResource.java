package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.CreateWorkoutController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.UpdateWorkoutController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.ViewAllWorkoutsController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.ViewSingleWorkoutController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToCreate;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutAndMuscleGroupNamesViewModel;
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
    private ViewSingleWorkoutController viewSingleWorkoutController;

    @Autowired
    public WorkoutsResource(
            CreateWorkoutController createWorkoutController,
            UpdateWorkoutController updateWorkoutController,
            ViewAllWorkoutsController viewAllWorkoutsController,
            ViewSingleWorkoutController viewSingleWorkoutController) {
        this.createWorkoutController = createWorkoutController;
        this.updateWorkoutController = updateWorkoutController;
        this.viewAllWorkoutsController = viewAllWorkoutsController;
        this.viewSingleWorkoutController = viewSingleWorkoutController;
    }

    @PostMapping(path = "/api/workouts")
    public ResponseEntity<WorkoutAndMuscleGroupNamesViewModel> createWorkout(@RequestBody WorkoutToCreate request) {
        ResponseEntity<WorkoutAndMuscleGroupNamesViewModel> createdWorkout = createWorkoutController.createWorkout(request);

        logger.info("All workouts:{}", createdWorkout.getBody());

        return createdWorkout;
    }

    @GetMapping(path = "/api/workouts")
    public ResponseEntity<WorkoutAndMuscleGroupNamesViewModel> viewWorkoutWithId(@RequestParam("workoutGid") String workoutGid) {
        ResponseEntity<WorkoutAndMuscleGroupNamesViewModel> workout = viewSingleWorkoutController.viewWorkoutWithId(workoutGid);

        logger.info("All workouts:{}", workout);

        return workout;
    }

    @PutMapping(path = "/api/workouts")
    public ResponseEntity<WorkoutFullViewModel> updateWorkout(@RequestBody WorkoutToUpdate request) {
        ResponseEntity<WorkoutFullViewModel> workout = updateWorkoutController.updateWorkout(request);

        logger.info("Updated workout {}", workout.getBody());

        return workout;
    }

    @GetMapping(path = "/api/workouts/overview")
    public ResponseEntity<WorkoutsInOverviewViewModel> viewAllWorkouts() {
        ResponseEntity<WorkoutsInOverviewViewModel> workouts = viewAllWorkoutsController.viewAllWorkouts();

        logger.info("All workouts:{}", workouts);

        return workouts;
    }

}
