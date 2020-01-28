package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.RestMuscleGroupsPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/muscleGroups")
public class MuscleGroupsResource {

    @Autowired
    public MuscleGroupsResource(MuscleGroupsController muscleGroupsController) {
        this.muscleGroupsController = muscleGroupsController;
    }

    private MuscleGroupsController muscleGroupsController;

    @GetMapping
    public ResponseEntity<MuscleGroupsViewModel> fetchAll() {
        var presenter = new RestMuscleGroupsPresenter();

        this.muscleGroupsController.viewAllMuscleGroups(presenter);

        return presenter.getResponse();
    }
}
