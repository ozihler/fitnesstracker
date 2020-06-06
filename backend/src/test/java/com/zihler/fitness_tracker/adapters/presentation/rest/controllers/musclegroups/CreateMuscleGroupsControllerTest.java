package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.musclegroups.InMemoryMuscleGroupsExercisesRepository;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.CreateMuscleGroupsRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupsViewModel;
import com.zihler.fitness_tracker.domain.values.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A CreateMuscleGroupsController")
class CreateMuscleGroupsControllerTest {
    @Test
    @DisplayName("can create new muscle groups from input string containing multiple muscle groups")
    void canCreateNewMuscleGroups() {
        var repo = new InMemoryMuscleGroupsExercisesRepository();
        var controller = new CreateMuscleGroupsController(repo);
        var request = new CreateMuscleGroupsRequest();
        request.setMuscleGroupNames(" Chest,   ;bIcEps; TricEps. ");
        var response = controller.createMuscleGroups(request);

        // check body returns 3 muscle groups from previous string
        MuscleGroupsViewModel body = response.getBody();
        assertEquals(3, body.getMuscleGroups().size());
        assertTrue(body.getMuscleGroups().contains(new MuscleGroupViewModel("Chest")));
        assertTrue(body.getMuscleGroups().contains(new MuscleGroupViewModel("Biceps")));
        assertTrue(body.getMuscleGroups().contains(new MuscleGroupViewModel("Triceps")));

        // check repo contains muscle groups
        assertEquals(3, repo.fetchAll().getMuscleGroups().size());
        assertNotNull(repo.by(Name.of("Chest")));
        assertNotNull(repo.by(Name.of("Biceps")));
        assertNotNull(repo.by(Name.of("Triceps")));
    }

}
