package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.requests.SetDetails;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.RestSetPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
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

    @PostMapping(path = "api/exercises/{exerciseName}/sets")
    public ResponseEntity<SetViewModel> addSetToExercise(
            @PathVariable("exerciseName") String exerciseName,
            @RequestBody() SetDetails setDetails) {

        var output = new RestSetPresenter();

        addSetToExerciseController.addSetToExercises(exerciseName, setDetails, output);
        logger.debug("add set {} to exercise {}, response {}", setDetails, exerciseName, output.getResponse().getBody());

        return output.getResponse();
    }
}
