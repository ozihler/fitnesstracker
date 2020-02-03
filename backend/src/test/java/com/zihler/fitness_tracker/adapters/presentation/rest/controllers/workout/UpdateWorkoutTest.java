package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.RestWorkoutPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.*;

class UpdateWorkoutTest {

    @Test
    void updateWorkoutTest() {

        ZonedDateTime workoutCreationTime = ZonedDateTime.now();
        WorkoutTitle workoutTitle = WorkoutTitle.from("Title");
        MuscleGroups muscleGroups = new MuscleGroups(new HashSet<>());

        FetchWorkout fetchWorkout = id -> Workout.update(id, workoutCreationTime, workoutTitle, muscleGroups);
        FetchMuscleGroup fetchMuscleGroup = MuscleGroup::new;
        StoreWorkout storeWorkout = workout -> workout;


        var controller = new UpdateWorkoutController(fetchWorkout, storeWorkout, fetchMuscleGroup);

        Set<MuscleGroupViewModel> updatedMuscleGroups = new HashSet<>();
        updatedMuscleGroups.add(new MuscleGroupViewModel("Chest"));
        updatedMuscleGroups.add(new MuscleGroupViewModel("Triceps"));

        long gid = 1234L;
        String newTitle = "New Title";
        long creationDate = workoutCreationTime.toInstant().toEpochMilli();

        WorkoutViewModel request = new WorkoutViewModel(gid, creationDate, newTitle, updatedMuscleGroups);

        RestWorkoutPresenter output = new RestWorkoutPresenter();

        //when
        controller.updateWorkout(request, output);

        // then
        WorkoutViewModel updatedWorkout = output.getResponse().getBody();

        assertNotNull(updatedWorkout);
        assertEquals(gid, updatedWorkout.getGid());
        assertEquals(newTitle, updatedWorkout.getTitle());
        assertEquals(creationDate, updatedWorkout.getCreationDate());
        assertEquals(2, updatedWorkout.getMuscleGroups().size());
        Set<String> workoutNames = updatedWorkout.getMuscleGroups().stream().map(MuscleGroupViewModel::getName).collect(toSet());
        assertTrue(workoutNames.contains("Chest"));
        assertTrue(workoutNames.contains("Triceps"));
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
