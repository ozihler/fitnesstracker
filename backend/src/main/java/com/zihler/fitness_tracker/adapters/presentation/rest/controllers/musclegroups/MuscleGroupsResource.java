package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.ViewAllExercisesController;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.RestExercisesPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.RestMuscleGroupsPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MuscleGroupsResource {

    private CreateMuscleGroupsController createMuscleGroupsController;
    private ViewMuscleGroupsController viewMuscleGroupsController;
    private ViewAllExercisesController viewAllExercisesController;

    @Autowired
    public MuscleGroupsResource(ViewMuscleGroupsController viewMuscleGroupsController,
                                CreateMuscleGroupsController createMuscleGroupsController,
                                ViewAllExercisesController viewAllExercisesController) {
        this.viewMuscleGroupsController = viewMuscleGroupsController;
        this.createMuscleGroupsController = createMuscleGroupsController;
        this.viewAllExercisesController = viewAllExercisesController;
    }

    @GetMapping(path = "/muscle-groups")
    public ResponseEntity<MuscleGroupsViewModel> fetchAll() {
        var presenter = new RestMuscleGroupsPresenter();

        this.viewMuscleGroupsController.viewAllMuscleGroups(presenter);

        return presenter.getResponse();
    }

    @GetMapping(path = "/muscle-groups/{muscleGroupName}/exercises")
    public ResponseEntity<ExercisesViewModel> fetchAllExercisesForMuscleGroup(@PathVariable("muscleGroupName") String muscleGroupName) {
        var output = new RestExercisesPresenter();

        viewAllExercisesController.fetchAllExercisesForMuscleGroup(muscleGroupName, output);

        return output.getResponse();
    }

    @PostMapping(path = "/muscle-groups")
    public ResponseEntity<MuscleGroupsViewModel> createMuscleGroup(@RequestBody CreateMuscleGroupsRequest request) {
        var presenter = new RestMuscleGroupsPresenter();

        createMuscleGroupsController.createMuscleGroups(request, presenter);

        return presenter.getResponse();
    }

}
