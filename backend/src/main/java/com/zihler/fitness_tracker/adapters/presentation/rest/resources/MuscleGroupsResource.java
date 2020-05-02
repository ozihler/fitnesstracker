package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.CreateMuscleGroupsController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.MakeMuscleGroupUnselectableController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.ViewMuscleGroupsController;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.CreateMuscleGroupsRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupsViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MuscleGroupsResource {
    private static final Logger logger = LoggerFactory.getLogger(MuscleGroupsResource.class);

    private CreateMuscleGroupsController createMuscleGroupsController;
    private ViewMuscleGroupsController viewMuscleGroupsController;
    private MakeMuscleGroupUnselectableController makeMuscleGroupUnselectableController;

    @Autowired
    public MuscleGroupsResource(ViewMuscleGroupsController viewMuscleGroupsController,
                                CreateMuscleGroupsController createMuscleGroupsController,
                                MakeMuscleGroupUnselectableController makeMuscleGroupUnselectableController) {
        this.viewMuscleGroupsController = viewMuscleGroupsController;
        this.createMuscleGroupsController = createMuscleGroupsController;
        this.makeMuscleGroupUnselectableController = makeMuscleGroupUnselectableController;
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

    @DeleteMapping(path = "/api/muscle-groups/{muscleGroupName}")
    public ResponseEntity<MuscleGroupViewModel> makeMuscleGroupUnselectable(@PathVariable("muscleGroupName") String muscleGroupName) {
        ResponseEntity<MuscleGroupViewModel> deletedMuscleGroup = makeMuscleGroupUnselectableController.makeUnselectable(muscleGroupName);

        logger.info("create muscle groups {}", deletedMuscleGroup.getBody());

        return deletedMuscleGroup;
    }


}
