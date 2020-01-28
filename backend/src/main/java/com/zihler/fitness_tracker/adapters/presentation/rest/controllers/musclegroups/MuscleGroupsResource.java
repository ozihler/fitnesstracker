package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.RestMuscleGroupsPresenter;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MuscleGroupsResource {

    @Autowired
    public MuscleGroupsResource(MuscleGroupsController muscleGroupsController) {
        this.muscleGroupsController = muscleGroupsController;
    }

    private MuscleGroupsController muscleGroupsController;

    @GetMapping(path = "/muscle-groups")
    public ResponseEntity<MuscleGroupsViewModel> fetchAll() {
        var presenter = new RestMuscleGroupsPresenter();

        this.muscleGroupsController.viewAllMuscleGroups(presenter);

        return presenter.getResponse();
    }

    @PostMapping(path = "/muscle-groups")
    public ResponseEntity<MuscleGroupsViewModel> createMuscleGroup(@RequestBody CreateMuscleGroupsRequest request) {
        var presenter = new RestMuscleGroupsPresenter();

        var input = new MuscleGroupsInput(request);

        MuscleGroupsDocument muscleGroups = input.muscleGroups();

        // todo implement store muscle groups use case

        presenter.present(muscleGroups);

        return presenter.getResponse();
    }
}
