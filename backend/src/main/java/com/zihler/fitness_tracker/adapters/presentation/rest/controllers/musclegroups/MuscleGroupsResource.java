package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.create_muscle_groups.CreateMuscleGroupsController;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.RestMuscleGroupsPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MuscleGroupsResource {

    private CreateMuscleGroupsController createMuscleGroupsController;

    @Autowired
    public MuscleGroupsResource(MuscleGroupsController viewMuscleGroupsController, CreateMuscleGroupsController createMuscleGroupsController) {
        this.viewMuscleGroupsController = viewMuscleGroupsController;
        this.createMuscleGroupsController = createMuscleGroupsController;
    }

    private MuscleGroupsController viewMuscleGroupsController;

    @GetMapping(path = "/muscle-groups")
    public ResponseEntity<MuscleGroupsViewModel> fetchAll() {
        var presenter = new RestMuscleGroupsPresenter();

        this.viewMuscleGroupsController.viewAllMuscleGroups(presenter);

        return presenter.getResponse();
    }

    @PostMapping(path = "/muscle-groups")
    public ResponseEntity<MuscleGroupsViewModel> createMuscleGroup(@RequestBody CreateMuscleGroupsRequest request) {
        var presenter = new RestMuscleGroupsPresenter();

        createMuscleGroupsController.createMuscleGroups(request, presenter);

        return presenter.getResponse();
    }

}
