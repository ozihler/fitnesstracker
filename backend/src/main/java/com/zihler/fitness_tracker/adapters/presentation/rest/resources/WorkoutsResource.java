package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.*;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutIdRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutAndMuscleGroupNamesViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutIdViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class WorkoutsResource {

    private static final Logger logger = LoggerFactory.getLogger(WorkoutsResource.class);
    private final CreateWorkoutController createWorkoutController;
    private final UpdateWorkoutController updateWorkoutController;
    private final ViewAllWorkoutsController viewAllWorkoutsController;
    private final DownloadAllWorkoutsController downloadAllWorkoutsController;
    private final ViewSingleWorkoutController viewSingleWorkoutController;
    private final CopyWorkoutController copyWorkoutController;
    private final DeleteWorkoutController deleteWorkoutController;

    @Autowired
    public WorkoutsResource(
            CreateWorkoutController createWorkoutController,
            UpdateWorkoutController updateWorkoutController,
            ViewAllWorkoutsController viewAllWorkoutsController,
            DownloadAllWorkoutsController downloadAllWorkoutsController,
            ViewSingleWorkoutController viewSingleWorkoutController,
            CopyWorkoutController copyWorkoutController, DeleteWorkoutController deleteWorkoutController) {
        this.createWorkoutController = createWorkoutController;
        this.updateWorkoutController = updateWorkoutController;
        this.viewAllWorkoutsController = viewAllWorkoutsController;
        this.downloadAllWorkoutsController = downloadAllWorkoutsController;
        this.viewSingleWorkoutController = viewSingleWorkoutController;
        this.copyWorkoutController = copyWorkoutController;
        this.deleteWorkoutController = deleteWorkoutController;
    }

    @PostMapping(path = "/api/workouts")
    public ResponseEntity<WorkoutAndMuscleGroupNamesViewModel> createWorkout() {
        ResponseEntity<WorkoutAndMuscleGroupNamesViewModel> createdWorkout = createWorkoutController.createWorkout();

        logger.info("All workouts:{}", createdWorkout.getBody());

        return createdWorkout;
    }

    @GetMapping(path = "/api/workouts")
    public ResponseEntity<WorkoutViewModel> viewWorkoutWithId(@RequestParam("workoutId") String workoutId) {
        ResponseEntity<WorkoutViewModel> workout = viewSingleWorkoutController.viewWorkoutWithId(workoutId);

        logger.info("All workouts:{}", workout);

        return workout;
    }

    @PutMapping(path = "/api/workouts")
    public ResponseEntity<WorkoutViewModel> updateWorkout(@RequestBody WorkoutToUpdate request) {
        ResponseEntity<WorkoutViewModel> workout = updateWorkoutController.updateWorkout(request);

        logger.info("Updated workout {}", workout.getBody());

        return workout;
    }

    @GetMapping(path = "/api/workouts/overview")
    public ResponseEntity<WorkoutsViewModel> viewAllWorkouts() {
        ResponseEntity<WorkoutsViewModel> workouts = viewAllWorkoutsController.viewAllWorkouts();

        logger.info("All workouts:{}", workouts);

        return workouts;
    }

    @GetMapping(path = "/api/workouts/download")
    public ResponseEntity<InputStreamResource> downloadAllWorkouts() {
        ResponseEntity<InputStreamResource> workouts = downloadAllWorkoutsController.downloadAllWorkouts();

        logger.info("All workouts:{}", workouts);

        return workouts;
    }

    @PostMapping(path = "/api/workouts/copy")
    public ResponseEntity<WorkoutIdViewModel> copyWorkout(@RequestBody WorkoutIdRequest request) {
        ResponseEntity<WorkoutIdViewModel> idOfCopiedWorkout = copyWorkoutController.copyWorkout(request);

        logger.info("id of copied workout:{}", idOfCopiedWorkout);

        return idOfCopiedWorkout;
    }

    @DeleteMapping(path = "/api/workouts/{workoutId}")
    public ResponseEntity<WorkoutViewModel> deleteWorkout(@PathVariable("workoutId") String workoutId) {
        ResponseEntity<WorkoutViewModel> deletedWorkout = deleteWorkoutController.deleteWorkout(workoutId);

        logger.info("deleted workout with id {}", Objects.requireNonNull(deletedWorkout.getBody()).getWorkoutId());

        return deletedWorkout;
    }

}
