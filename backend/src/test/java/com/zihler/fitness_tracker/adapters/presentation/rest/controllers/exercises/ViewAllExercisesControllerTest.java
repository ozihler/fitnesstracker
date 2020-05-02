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

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class ViewAllExercisesControllerTest {

    @Test
    void test() {
        MuscleGroup expectedMuscleGroup =   MuscleGroup.of(Name.of("Chest"));
        List<Exercise> expectedExercises = List.of(
                  Exercise.of("Bench Press"),
                  Exercise.of("Dumbbell Bench Press"),
                  Exercise.of("Overhead Dumbbell Pull"));

        FetchExercises fetchExercises = (muscleGroup -> Exercises.of(expectedExercises));
        FetchMuscleGroup fetchMuscleGroup = name -> expectedMuscleGroup;

        var controller = new ViewAllExercisesController(fetchExercises);

        ResponseEntity<ExercisesViewModel> exercisesOfMuscleGroup = controller.viewAllExercisesOfMuscleGroup("Chest");

        ExercisesViewModel actualExercisesResponse = exercisesOfMuscleGroup.getBody();
        assertNotNull(actualExercisesResponse);

        List<ExerciseViewModel> actualExercises = actualExercisesResponse.getExercises();
        assertEquals(3, actualExercises.size());
        List<String> actualExerciseNames = actualExercises.stream().map(ExerciseViewModel::getName).collect(toList());
        List<String> expectedExercisesNames = expectedExercises.stream().map(Exercise::getName).map(Name::toString).collect(toList());
        assertTrue(actualExerciseNames.containsAll(expectedExercisesNames));
    }

}
