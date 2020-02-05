package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.CreateMuscleGroupsController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.ViewMuscleGroupsController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.CreateMuscleGroupsRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupsViewModel;
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
    public ResponseEntity<MuscleGroupsViewModel> viewAllMuscleGroups() {
        var muscleGroups = viewMuscleGroupsController.viewAllMuscleGroups();

        logger.info("View all muscle groups {}", muscleGroups.getBody());

        return muscleGroups;
    }

    @PostMapping(path = "/api/muscle-groups")
    public ResponseEntity<MuscleGroupsViewModel> createMuscleGroups(@RequestBody CreateMuscleGroupsRequest request) {
        ResponseEntity<MuscleGroupsViewModel> muscleGroups = createMuscleGroupsController.createMuscleGroups(request);

        logger.info("create muscle groups {}", muscleGroups.getBody());

        return muscleGroups;
    }


}
