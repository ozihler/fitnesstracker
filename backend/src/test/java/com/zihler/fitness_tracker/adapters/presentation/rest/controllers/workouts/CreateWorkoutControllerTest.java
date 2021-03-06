package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutAndMuscleGroupNamesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateWorkoutControllerTest {

    @Test
    void createWorkout() {
        // given
        StoreWorkout storeWorkout = workout -> workout;
        var controller = new CreateWorkoutController(storeWorkout, () -> WorkoutId.of("12345"));
        CreationDate before = CreationDate.of(LocalDate.now());

        // when
        WorkoutAndMuscleGroupNamesViewModel result = controller.createWorkout().getBody();

        assertNotNull(result);
        assertThat(result.getMuscleGroups().size()).isEqualTo(0);
        assertTrue(result.getCreationDate() >= before.toMillis());
        assertThat(result.getWorkoutId()).isEqualTo("12345");
    }

}
