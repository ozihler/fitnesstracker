package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.CreateExercisesController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.ViewAllExercisesController;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.RestExercisesPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.RestMuscleGroupsPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseNames;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MuscleGroupsResource {

    private CreateMuscleGroupsController createMuscleGroupsController;
    private ViewMuscleGroupsController viewMuscleGroupsController;
    private ViewAllExercisesController viewAllExercisesController;
    private CreateExercisesController createExercises;

    @Autowired
    public MuscleGroupsResource(ViewMuscleGroupsController viewMuscleGroupsController,
                                CreateMuscleGroupsController createMuscleGroupsController,
                                ViewAllExercisesController viewAllExercisesController, CreateExercisesController createExercises) {
        this.viewMuscleGroupsController = viewMuscleGroupsController;
        this.createMuscleGroupsController = createMuscleGroupsController;
        this.viewAllExercisesController = viewAllExercisesController;
        this.createExercises = createExercises;
    }

    @GetMapping(path = "/api/muscle-groups")
    public ResponseEntity<MuscleGroupsViewModel> fetchAll() {
        var presenter = new RestMuscleGroupsPresenter();

        this.viewMuscleGroupsController.viewAllMuscleGroups(presenter);

        return presenter.getResponse();
    }

    @PostMapping(path = "/api/muscle-groups")
    public ResponseEntity<MuscleGroupsViewModel> createMuscleGroup(@RequestBody CreateMuscleGroupsRequest request) {
        var presenter = new RestMuscleGroupsPresenter();

        createMuscleGroupsController.createMuscleGroups(request, presenter);

        return presenter.getResponse();
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
//    @PostMapping(path = "/muscle-groups/{muscleGroupName}/exercises/{exerciseName}/")
//    public ResponseEntity<ExercisesViewModel> createExercisesForMuscleGroup(
//            @PathVariable("muscleGroupName") String muscleGroupName,
//            @PathVariable("exerciseName") String exerciseName,
//            @RequestBody() ExerciseNames setDetails) {
//
//        var output = new RestExercisesPresenter();
//
//        createExercises.forMuscleGroup(muscleGroupName, exercisesNames, output);
//
//        return output.getResponse();
//    }

}
