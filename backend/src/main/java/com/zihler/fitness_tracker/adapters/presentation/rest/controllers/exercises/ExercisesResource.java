package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests.ExerciseNames;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.RestExercisesPresenter;
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

    @Autowired
    public ExercisesResource(ViewAllExercisesController viewAllExercisesController, CreateExercisesController createExercises) {
        this.viewAllExercisesController = viewAllExercisesController;
        this.createExercises = createExercises;
    }

    @GetMapping(path = "/api/muscle-groups/{muscleGroupName}/exercises")
    public ResponseEntity<ExercisesViewModel> fetchAllExercisesForMuscleGroup(@PathVariable("muscleGroupName") String muscleGroupName) {
        var output = new RestExercisesPresenter();

        viewAllExercisesController.fetchAllExercisesForMuscleGroup(muscleGroupName, output);
        logger.info("fetch all exercises for muscle group {}, {}", muscleGroupName, output.getResponse().getBody());

        return output.getResponse();
    }

    @PostMapping(path = "/api/muscle-groups/{muscleGroupName}/exercises")
    public ResponseEntity<ExercisesViewModel> createExercisesForMuscleGroup(@PathVariable("muscleGroupName") String muscleGroupName, @RequestBody() ExerciseNames exercisesNames) {
        var output = new RestExercisesPresenter();

        createExercises.forMuscleGroup(muscleGroupName, exercisesNames, output);
        logger.info("create exercises for muscle group {}, {}, {}", muscleGroupName, exercisesNames, output.getResponse().getBody());

        return output.getResponse();
    }

}
