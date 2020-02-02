package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

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
    private ViewMuscleGroupsController viewMuscleGroupsController;

    @Autowired
    public MuscleGroupsResource(ViewMuscleGroupsController viewMuscleGroupsController,
                                CreateMuscleGroupsController createMuscleGroupsController) {
        this.viewMuscleGroupsController = viewMuscleGroupsController;
        this.createMuscleGroupsController = createMuscleGroupsController;
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


}
