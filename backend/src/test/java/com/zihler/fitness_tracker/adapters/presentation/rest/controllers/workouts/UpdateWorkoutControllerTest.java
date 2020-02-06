package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.ExerciseFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.MuscleGroupFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutFullViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercise;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.*;

class UpdateWorkoutControllerTest {

    @Test
    void updateWorkoutTest() {
        ZonedDateTime workoutCreationTime = ZonedDateTime.now();

        FetchWorkout fetchWorkout = id -> new Workout(id, workoutCreationTime, WorkoutTitle.of("Title"), new MuscleGroups(new HashSet<>()));
        FetchMuscleGroup fetchMuscleGroup = MuscleGroup::new;
        StoreWorkout storeWorkout = workout -> workout;

        FetchExercise fetchExercise = exerciseName -> new Exercise(null, exerciseName);
        var controller = new UpdateWorkoutController(fetchWorkout, storeWorkout, fetchMuscleGroup, fetchExercise);


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
                        new ExerciseFullViewModel("Lat Pull", List.of(
                                SetViewModel.of(7, 50, 12, 45),
                                SetViewModel.of(8, 50, 12, 45),
                                SetViewModel.of(9, 50, 12, 45)
                        ))
                ))

        ));

        WorkoutToUpdate jsonRequest = new WorkoutToUpdate(updatedWorkoutRequest);

        var fullWorkoutViewModel = controller.updateWorkout(jsonRequest).getBody();

        // Workout
        assertNotNull(fullWorkoutViewModel);
        assertEquals(gid, fullWorkoutViewModel.getGid());
        assertEquals(newTitle, fullWorkoutViewModel.getTitle());
        assertEquals(creationDate, fullWorkoutViewModel.getCreationDate());

        // Muscle groups
        assertEquals(2, fullWorkoutViewModel.getMuscleGroups().size());
        Set<String> muscleGroupNames = fullWorkoutViewModel.getMuscleGroups().stream().map(MuscleGroupFullViewModel::getName).collect(toSet());
        assertTrue(muscleGroupNames.contains("Chest"));
        assertTrue(muscleGroupNames.contains("Triceps"));

        // Exercises
        Set<String> exerciseNames = fullWorkoutViewModel.getMuscleGroups()
                .stream()
                .map(MuscleGroupFullViewModel::getExercises)
                .flatMap(Collection::stream)
                .map(ExerciseFullViewModel::getName)
                .collect(toSet());

        assertEquals(3, exerciseNames.size());
        assertTrue(exerciseNames.contains("Bench Press"));
        assertTrue(exerciseNames.contains("Lat Pull"));
        assertTrue(exerciseNames.contains("Flying"));

    }
}
