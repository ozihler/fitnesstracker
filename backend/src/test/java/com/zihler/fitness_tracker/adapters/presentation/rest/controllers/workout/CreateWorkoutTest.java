package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout.requests.WorkoutToCreateRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestWorkoutPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
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
        WorkoutToCreateRequest request = new WorkoutToCreateRequest("Title");
        RestWorkoutPresenter output = new RestWorkoutPresenter();
        ZonedDateTime before = ZonedDateTime.now();

        // when
        controller.createWorkout(request, output);

        // then
        ZonedDateTime after = ZonedDateTime.now();

        WorkoutViewModel result = output.getResponse().getBody();

        assertNotNull(result);
        assertThat(result.getTitle()).isEqualTo(request.getTitle());
        assertThat(result.getMuscleGroups().size()).isEqualTo(0);
        assertTrue(result.getCreationDate() > before.toInstant().toEpochMilli());
        assertTrue(result.getCreationDate() < after.toInstant().toEpochMilli());
        assertThat(result.getGid()).isPositive();
    }

}
