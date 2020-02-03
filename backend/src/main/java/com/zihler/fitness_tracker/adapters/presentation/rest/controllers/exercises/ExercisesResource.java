package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.RestExercisesPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.set.RestSetPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseNames;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetDetails;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExercisesResource {

    private ViewAllExercisesController viewAllExercisesController;
    private CreateExercisesController createExercises;
    private AddSetToExercisesController addSetToExerciseController;

    @Autowired
    public ExercisesResource(ViewAllExercisesController viewAllExercisesController, CreateExercisesController createExercises, AddSetToExercisesController addSetToExerciseController) {

        this.viewAllExercisesController = viewAllExercisesController;
        this.createExercises = createExercises;
        this.addSetToExerciseController = addSetToExerciseController;
    }

    @GetMapping(path = "/api/muscle-groups/{muscleGroupName}/exercises")
    public ResponseEntity<ExercisesViewModel> fetchAllExercisesForMuscleGroup(@PathVariable("muscleGroupName") String muscleGroupName) {
        var output = new RestExercisesPresenter();

        viewAllExercisesController.fetchAllExercisesForMuscleGroup(muscleGroupName, output);

        return output.getResponse();
    }

    @PostMapping(path = "/api/muscle-groups/{muscleGroupName}/exercises")
    public ResponseEntity<ExercisesViewModel> createExercisesForMuscleGroup(@PathVariable("muscleGroupName") String muscleGroupName, @RequestBody() ExerciseNames exercisesNames) {
        var output = new RestExercisesPresenter();

        createExercises.forMuscleGroup(muscleGroupName, exercisesNames, output);

        return output.getResponse();
    }

    @PostMapping(path = "api/exercises/{exerciseName}/sets")
    public ResponseEntity<SetViewModel> createExercisesForMuscleGroup(
            @PathVariable("exerciseName") String exerciseName,
            @RequestBody() SetDetails setDetails) {

        var output = new RestSetPresenter();

        addSetToExerciseController.addSetToExercises(exerciseName, setDetails, output);

        return output.getResponse();
    }

}
