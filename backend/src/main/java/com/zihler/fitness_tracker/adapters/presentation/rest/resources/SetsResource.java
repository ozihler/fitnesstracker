package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.AddSetToExercisesController;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetsResource {
    private static final Logger logger = LoggerFactory.getLogger(SetsResource.class);

    private final AddSetToExercisesController addSetToExerciseController;

    public SetsResource(AddSetToExercisesController addSetToExerciseController) {
        this.addSetToExerciseController = addSetToExerciseController;
    }

    @PostMapping(path = "api/workouts/{workoutId}/exercises/{exerciseName}/sets")
    public ResponseEntity<SetViewModel> addSetToExercise(
            @PathVariable("workoutId") String workoutId,
            @PathVariable("exerciseName") String exerciseName,
            @RequestBody() SetViewModel setToAdd) {

        var addedSet = addSetToExerciseController.addSetToExercise(workoutId, exerciseName, setToAdd);

        logger.info("add set {} to exercise {}, response {}", setToAdd, exerciseName, addedSet.getBody());

        return addedSet;
    }

}
