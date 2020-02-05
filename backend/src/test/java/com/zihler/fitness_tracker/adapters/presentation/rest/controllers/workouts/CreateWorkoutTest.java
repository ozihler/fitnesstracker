package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToCreate;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutAndMuscleGroupNamesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateWorkoutTest {

    @Test
    void createWorkout() {
        // given
        StoreWorkout storeWorkout = workout -> workout;
        var controller = new CreateWorkoutController(storeWorkout);
        WorkoutToCreate request = new WorkoutToCreate("Title");
        ZonedDateTime before = ZonedDateTime.now();

        // when
        WorkoutAndMuscleGroupNamesViewModel result = controller.createWorkout(request).getBody();

        ZonedDateTime after = ZonedDateTime.now();

        assertNotNull(result);
        assertThat(result.getTitle()).isEqualTo(request.getTitle());
        assertThat(result.getMuscleGroups().size()).isEqualTo(0);
        assertTrue(result.getCreationDate() > before.toInstant().toEpochMilli());
        assertTrue(result.getCreationDate() < after.toInstant().toEpochMilli());
        assertThat(result.getGid()).isPositive();
    }

}
