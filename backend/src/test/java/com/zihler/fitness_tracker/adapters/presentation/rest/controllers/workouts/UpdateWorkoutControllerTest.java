package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class UpdateWorkoutControllerTest {

    @Test
    void updateWorkoutTest() {
        LocalDate workoutCreationTime = LocalDate.now();

        CreationDate creationDate = CreationDate.of(workoutCreationTime);
        FetchWorkout fetchWorkout = id -> new Workout(id, creationDate, WorkoutTitle.of("Title"), new MuscleGroups(new ArrayList<>()));
        StoreWorkout storeWorkout = workout -> workout;

        var controller = new UpdateWorkoutController(fetchWorkout, storeWorkout);


        String workoutId = "1234L";
        String newTitle = "New Title";

        var updatedWorkoutRequest = new WorkoutViewModel(workoutId, creationDate.toMillis(), newTitle, List.of(
                new MuscleGroupViewModel("Chest", List.of(
                        new ExerciseViewModel("Bench Press", List.of(
                                SetViewModel.of(50, 12, 45),
                                SetViewModel.of(50, 12, 45),
                                SetViewModel.of(50, 12, 45)
                        ), 1,true),
                        new ExerciseViewModel("Flying", List.of(
                                SetViewModel.of(50, 12, 45),
                                SetViewModel.of(50, 12, 45),
                                SetViewModel.of(50, 12, 45)
                        ), 1,true)

                ),true),
                new MuscleGroupViewModel("Triceps", List.of(
                        new ExerciseViewModel("Lat Pull", List.of(
                                SetViewModel.of(50, 12, 45),
                                SetViewModel.of(50, 12, 45),
                                SetViewModel.of(50, 12, 45)
                        ),1,true)
                ),true)

        ));

        WorkoutToUpdate jsonRequest = new WorkoutToUpdate(updatedWorkoutRequest);

        var fullWorkoutViewModel = controller.updateWorkout(jsonRequest).getBody();

        // Workout
        assertNotNull(fullWorkoutViewModel);
        assertEquals(workoutId, fullWorkoutViewModel.getWorkoutId());
        assertEquals(newTitle, fullWorkoutViewModel.getTitle());
        assertEquals(creationDate.toMillis(), fullWorkoutViewModel.getCreationDate());

        // Muscle groups
        assertEquals(2, fullWorkoutViewModel.getMuscleGroups().size());
        List<String> muscleGroupNames = fullWorkoutViewModel.getMuscleGroups().stream().map(MuscleGroupViewModel::getName).collect(toList());
        assertTrue(muscleGroupNames.contains("Chest"));
        assertTrue(muscleGroupNames.contains("Triceps"));

        // Exercises
        List<String> exerciseNames = exercises(fullWorkoutViewModel)
                .stream()
                .map(ExerciseViewModel::getName)
                .collect(toList());

        assertEquals(3, exerciseNames.size());
        assertTrue(exerciseNames.contains("Bench Press"));
        assertTrue(exerciseNames.contains("Lat Pull"));
        assertTrue(exerciseNames.contains("Flying"));

        // sets
        long numberOfSetsInWholeWorkout = exercises(fullWorkoutViewModel)
                .stream()
                .map(ExerciseViewModel::getSets)
                .mapToLong(Collection::size)
                .sum();

        assertEquals(9, numberOfSetsInWholeWorkout);

    }

    private List<ExerciseViewModel> exercises(WorkoutViewModel workoutViewModel) {
        return workoutViewModel.getMuscleGroups()
                .stream()
                .map(MuscleGroupViewModel::getExercises)
                .flatMap(Collection::stream)
                .collect(toList());
    }
}
