package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullExerciseViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullMuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullWorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullSetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
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
        StoreWorkout storeWorkout = workout -> workout;

        var controller = new UpdateWorkoutController(fetchWorkout, storeWorkout);


        String gid = "1234L";
        String newTitle = "New Title";
        long creationDate = workoutCreationTime.toInstant().toEpochMilli();

        var updatedWorkoutRequest = new FullWorkoutViewModel(gid, creationDate, newTitle, Set.of(
                new FullMuscleGroupViewModel("Chest", List.of(
                        new FullExerciseViewModel("Bench Press", List.of(
                                FullSetViewModel.of(50, 12, 45),
                                FullSetViewModel.of(50, 12, 45),
                                FullSetViewModel.of(50, 12, 45)
                        )),
                        new FullExerciseViewModel("Flying", List.of(
                                FullSetViewModel.of(50, 12, 45),
                                FullSetViewModel.of(50, 12, 45),
                                FullSetViewModel.of(50, 12, 45)
                        ))

                )),
                new FullMuscleGroupViewModel("Triceps", List.of(
                        new FullExerciseViewModel("Lat Pull", List.of(
                                FullSetViewModel.of(50, 12, 45),
                                FullSetViewModel.of(50, 12, 45),
                                FullSetViewModel.of(50, 12, 45)
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
        Set<String> muscleGroupNames = fullWorkoutViewModel.getMuscleGroups().stream().map(FullMuscleGroupViewModel::getName).collect(toSet());
        assertTrue(muscleGroupNames.contains("Chest"));
        assertTrue(muscleGroupNames.contains("Triceps"));

        // Exercises
        Set<String> exerciseNames = exercises(fullWorkoutViewModel)
                .stream()
                .map(FullExerciseViewModel::getName)
                .collect(toSet());

        assertEquals(3, exerciseNames.size());
        assertTrue(exerciseNames.contains("Bench Press"));
        assertTrue(exerciseNames.contains("Lat Pull"));
        assertTrue(exerciseNames.contains("Flying"));

        // sets
        long numberOfSetsInWholeWorkout = exercises(fullWorkoutViewModel)
                .stream()
                .map(FullExerciseViewModel::getSets)
                .mapToLong(Collection::size)
                .sum();

        assertEquals(9, numberOfSetsInWholeWorkout);

    }

    private Set<FullExerciseViewModel> exercises(FullWorkoutViewModel fullWorkoutViewModel) {
        return fullWorkoutViewModel.getMuscleGroups()
                .stream()
                .map(FullMuscleGroupViewModel::getExercises)
                .flatMap(Collection::stream)
                .collect(toSet());
    }
}
