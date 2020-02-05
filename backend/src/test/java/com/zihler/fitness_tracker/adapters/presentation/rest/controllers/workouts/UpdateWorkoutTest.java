package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.ExerciseFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.MuscleGroupFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.*;

class UpdateWorkoutTest {

    @Test
    void updateWorkoutTest() {
        ZonedDateTime workoutCreationTime = ZonedDateTime.now();

        FetchWorkout fetchWorkout = id -> new Workout(id, workoutCreationTime, WorkoutTitle.of("Title"), new MuscleGroups(new HashSet<>()));
        FetchMuscleGroup fetchMuscleGroup = MuscleGroup::new;
        StoreWorkout storeWorkout = workout -> workout;


        var controller = new UpdateWorkoutController(fetchWorkout, storeWorkout, fetchMuscleGroup);


        long gid = 1234L;
        String newTitle = "New Title";
        long creationDate = workoutCreationTime.toInstant().toEpochMilli();

        var updatedWorkoutRequest = new WorkoutFullViewModel(gid, creationDate, newTitle, Set.of(
                new MuscleGroupFullViewModel("Chest", List.of(
                        new ExerciseFullViewModel("Bench Press", List.of(
                                SetViewModel.of(1, 50, 12, 45),
                                SetViewModel.of(2, 50, 12, 45),
                                SetViewModel.of(3, 50, 12, 45)
                        )),
                        new ExerciseFullViewModel("Flying", List.of(
                                SetViewModel.of(4, 50, 12, 45),
                                SetViewModel.of(5, 50, 12, 45),
                                SetViewModel.of(6, 50, 12, 45)
                        ))

                )),
                new MuscleGroupFullViewModel("Triceps", List.of(
                        new ExerciseFullViewModel("Bench Press", List.of(
                                SetViewModel.of(7, 50, 12, 45),
                                SetViewModel.of(8, 50, 12, 45),
                                SetViewModel.of(9, 50, 12, 45)
                        ))
                ))

        ));

        WorkoutToUpdate jsonRequest = new WorkoutToUpdate(updatedWorkoutRequest);

        var updatedWorkout = controller.updateWorkout(jsonRequest).getBody();

        // Workout
        assertNotNull(updatedWorkout);
        assertEquals(gid, updatedWorkout.getGid());
        assertEquals(newTitle, updatedWorkout.getTitle());
        assertEquals(creationDate, updatedWorkout.getCreationDate());

        // Muscle groups
        assertEquals(2, updatedWorkout.getMuscleGroups().size());
        Set<String> workoutNames = updatedWorkout.getMuscleGroups().stream().map(MuscleGroupFullViewModel::getName).collect(toSet());
        assertTrue(workoutNames.contains("Chest"));
        assertTrue(workoutNames.contains("Triceps"));

        // Exercises
//        updatedWorkout.getMuscleGroups().stream().map(m->m.)
    }
}
