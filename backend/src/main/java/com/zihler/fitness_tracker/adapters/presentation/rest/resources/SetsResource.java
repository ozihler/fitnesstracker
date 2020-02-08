package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.AddSetToExercisesController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.requests.SetDetails;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullSetViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetsResource {
    private static final Logger logger = LogManager.getLogger();

    private AddSetToExercisesController addSetToExerciseController;

    public SetsResource(AddSetToExercisesController addSetToExerciseController) {
        this.addSetToExerciseController = addSetToExerciseController;
    }

    @PostMapping(path = "api/workouts/{workoutId}/exercises/{exerciseName}/sets")
    public ResponseEntity<FullSetViewModel> addSetToExercise(
            @PathVariable("workoutId") String workoutId,
            @PathVariable("exerciseName") String exerciseName,
            @RequestBody() SetDetails setDetails) {

        var addedSet = addSetToExerciseController.addSetToExercise(workoutId, exerciseName, setDetails);

        logger.info("add set {} to exercise {}, response {}", setDetails, exerciseName, addedSet.getBody());

        return addedSet;
    }

}
