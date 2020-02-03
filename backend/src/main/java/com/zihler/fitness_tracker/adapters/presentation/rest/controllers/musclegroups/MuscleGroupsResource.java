package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.CreateMuscleGroupsRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.RestMuscleGroupsPresenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MuscleGroupsResource {
    private static final Logger logger = LogManager.getLogger();

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
        var output = new RestMuscleGroupsPresenter();

        this.viewMuscleGroupsController.viewAllMuscleGroups(output);
        logger.info("View all muscle groups {}", output.getResponse().getBody());

        return output.getResponse();
    }

    @PostMapping(path = "/api/muscle-groups")
    public ResponseEntity<MuscleGroupsViewModel> createMuscleGroups(@RequestBody CreateMuscleGroupsRequest request) {
        var output = new RestMuscleGroupsPresenter();

        createMuscleGroupsController.createMuscleGroups(request, output);
        logger.info("create muscle groups {}", output.getResponse().getBody());

        return output.getResponse();
    }


}
