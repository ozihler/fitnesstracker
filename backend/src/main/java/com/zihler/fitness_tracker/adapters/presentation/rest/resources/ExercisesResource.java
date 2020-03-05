package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.CreateExercisesController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.MakeExerciseUnselectableController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.ViewAllExercisesController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests.ExerciseNames;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseNameViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExercisesResource {
    private static final Logger logger = LogManager.getLogger();

    private ViewAllExercisesController viewAllExercisesController;
    private CreateExercisesController createExercises;
    private MakeExerciseUnselectableController makeExerciseUnselectableController;

    @Autowired
    public ExercisesResource(ViewAllExercisesController viewAllExercisesController,
                             CreateExercisesController createExercises,
                             MakeExerciseUnselectableController makeExerciseUnselectableController) {
        this.viewAllExercisesController = viewAllExercisesController;
        this.createExercises = createExercises;
        this.makeExerciseUnselectableController = makeExerciseUnselectableController;
    }

    @GetMapping(path = "/api/muscle-groups/{muscleGroupName}/exercises")
    public ResponseEntity<ExercisesViewModel> viewAllExercisesOfMuscleGroup(@PathVariable("muscleGroupName") String muscleGroupName) {
        ResponseEntity<ExercisesViewModel> exercises = viewAllExercisesController.viewAllExercisesOfMuscleGroup(muscleGroupName);

        logger.info("fetch all exercises for muscle group {}, {}", muscleGroupName, exercises.getBody());

        return exercises;
    }

    @PostMapping(path = "/api/muscle-groups/{muscleGroupName}/exercises")
    public ResponseEntity<ExercisesViewModel> createExercisesForMuscleGroup(
            @PathVariable("muscleGroupName") String muscleGroupName,
            @RequestBody() ExerciseNames exercisesNames) {
        ResponseEntity<ExercisesViewModel> exercises = createExercises.forMuscleGroup(muscleGroupName, exercisesNames);

        logger.info("create exercises for muscle group {}, names {}, response {}", muscleGroupName, exercisesNames, exercises.getBody());

        return exercises;
    }

    @DeleteMapping(path = "/api/exercises/{exerciseName}")
    public ResponseEntity<ExerciseNameViewModel> makeExerciseUnselectable(@PathVariable("exerciseName") String exerciseName) {
        ResponseEntity<ExerciseNameViewModel> unselectableExercises = makeExerciseUnselectableController.makeUnselectable(exerciseName);

        logger.info("create muscle groups {}", unselectableExercises.getBody());

        return unselectableExercises;
    }

}
