package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercises;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.*;

class ViewAllExercisesControllerTest {

    @Test
    void test() {
        MuscleGroup expectedMuscleGroup = new MuscleGroup(Name.of("Chest"));
        Set<Exercise> expectedExercises = Set.of(
                new Exercise(expectedMuscleGroup, Name.of("Bench Press")),
                new Exercise(expectedMuscleGroup, Name.of("Dumbbell Bench Press")),
                new Exercise(expectedMuscleGroup, Name.of("Overhead Dumbbell Pull")));

        FetchExercises fetchExercises = (muscleGroup -> Exercises.of(expectedExercises));
        FetchMuscleGroup fetchMuscleGroup = name -> expectedMuscleGroup;

        var controller = new ViewAllExercisesController(fetchExercises);

        ResponseEntity<ExercisesViewModel> exercisesOfMuscleGroup = controller.viewAllExercisesOfMuscleGroup("Chest");

        ExercisesViewModel actualExercisesResponse = exercisesOfMuscleGroup.getBody();
        assertNotNull(actualExercisesResponse);

        Set<ExerciseViewModel> actualExercises = actualExercisesResponse.getExercises();
        assertEquals(3, actualExercises.size());
        Set<String> actualExerciseNames = actualExercises.stream().map(ExerciseViewModel::getName).collect(toSet());
        Set<String> expectedExercisesNames = expectedExercises.stream().map(Exercise::getName).map(Name::toString).collect(toSet());
        assertTrue(actualExerciseNames.containsAll(expectedExercisesNames));
    }

}