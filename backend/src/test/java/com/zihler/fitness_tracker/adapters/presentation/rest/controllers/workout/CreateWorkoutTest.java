package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateWorkoutTest {

    @Test
    void createWorkout() {
        // given
        StoreWorkout storeWorkout = workout -> workout;
        var controller = new CreateWorkoutController(storeWorkout);
        WorkoutToCreateRequest request = new WorkoutToCreateRequest("Title");
        TestWorkoutPresenter output = new TestWorkoutPresenter();
        ZonedDateTime before = ZonedDateTime.now();

        // when
        controller.createWorkout(request, output);

        // then
        ZonedDateTime after = ZonedDateTime.now();

        WorkoutDocument result = output.getWorkoutDocument();

        assertThat(result.getWorkoutTitle().toString()).isEqualTo(request.getTitle());
        assertThat(result.getMuscleGroups().getMuscleGroups().size()).isEqualTo(0);
        assertTrue(result.getCreationDateTime().isAfter(before));
        assertTrue(result.getCreationDateTime().isBefore(after));
        assertThat(result.getGid().toLong()).isPositive();
    }

    private class TestWorkoutPresenter implements WorkoutPresenter {
        private WorkoutDocument workoutDocument;

        public WorkoutDocument getWorkoutDocument() {
            return workoutDocument;
        }

        @Override
        public void present(WorkoutDocument workoutDocument) {
            this.workoutDocument = workoutDocument;
        }
    }
}
