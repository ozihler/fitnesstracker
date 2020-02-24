package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.*;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullWorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToCreate;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.viewmodels.WorkoutsInOverviewGroupedByMuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutAndMuscleGroupNamesViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutIdViewModel;
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
    private ViewAllWorkoutsGroupedByMuscleGroupsController viewAllWorkoutsGroupedByMuscleGroupsController;
    private ViewSingleWorkoutController viewSingleWorkoutController;
    private CopyWorkoutController copyWorkoutController;

    @Autowired
    public WorkoutsResource(
            CreateWorkoutController createWorkoutController,
            UpdateWorkoutController updateWorkoutController,
            ViewAllWorkoutsController viewAllWorkoutsController,
            ViewAllWorkoutsGroupedByMuscleGroupsController viewAllWorkoutsGroupedByMuscleGroupsController,
            ViewSingleWorkoutController viewSingleWorkoutController,
            CopyWorkoutController copyWorkoutController) {
        this.createWorkoutController = createWorkoutController;
        this.updateWorkoutController = updateWorkoutController;
        this.viewAllWorkoutsController = viewAllWorkoutsController;
        this.viewAllWorkoutsGroupedByMuscleGroupsController = viewAllWorkoutsGroupedByMuscleGroupsController;
        this.viewSingleWorkoutController = viewSingleWorkoutController;
        this.copyWorkoutController = copyWorkoutController;
    }

    @PostMapping(path = "/api/workouts")
    public ResponseEntity<WorkoutAndMuscleGroupNamesViewModel> createWorkout(@RequestBody WorkoutToCreate request) {
        ResponseEntity<WorkoutAndMuscleGroupNamesViewModel> createdWorkout = createWorkoutController.createWorkout(request);

        logger.info("All workouts:{}", createdWorkout.getBody());

        return createdWorkout;
    }

    @GetMapping(path = "/api/workouts")
    public ResponseEntity<FullWorkoutViewModel> viewWorkoutWithId(@RequestParam("workoutGid") String workoutGid) {
        ResponseEntity<FullWorkoutViewModel> workout = viewSingleWorkoutController.viewWorkoutWithId(workoutGid);

        logger.info("All workouts:{}", workout);

        return workout;
    }

    @PutMapping(path = "/api/workouts")
    public ResponseEntity<FullWorkoutViewModel> updateWorkout(@RequestBody WorkoutToUpdate request) {
        ResponseEntity<FullWorkoutViewModel> workout = updateWorkoutController.updateWorkout(request);

        logger.info("Updated workout {}", workout.getBody());

        return workout;
    }

    @GetMapping(path = "/api/workouts/overview")
    public ResponseEntity<WorkoutsInOverviewViewModel> viewAllWorkouts() {
        ResponseEntity<WorkoutsInOverviewViewModel> workouts = viewAllWorkoutsController.viewAllWorkouts();

        logger.info("All workouts:{}", workouts);

        return workouts;
    }

    @GetMapping(path = "/api/workouts/overview/groupedByMuscleGroups")
    public ResponseEntity<WorkoutsInOverviewGroupedByMuscleGroupViewModel> viewAllWorkoutsGroupedByMuscleGroups() {
        ResponseEntity<WorkoutsInOverviewGroupedByMuscleGroupViewModel> workoutsGroupedByMuscleGroups = viewAllWorkoutsGroupedByMuscleGroupsController.viewAllWorkoutsGroupedByMuscleGroups();

        logger.info("All workouts:{}", workoutsGroupedByMuscleGroups);

        return workoutsGroupedByMuscleGroups;
    }

    @PostMapping(path = "/api/workouts/copy")
    public ResponseEntity<WorkoutIdViewModel> copyWorkout(@RequestBody WorkoutIdViewModel request) {
        ResponseEntity<WorkoutIdViewModel> idOfCopiedWorkout = copyWorkoutController.copyWorkout(request);

        logger.info("id of copied workout:{}", idOfCopiedWorkout);

        return idOfCopiedWorkout;
    }

}
