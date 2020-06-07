package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests.ExerciseNames;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreExercises;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.Multiplier;
import com.zihler.fitness_tracker.domain.values.Name;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateExercisesControllerTest {

    private MuscleGroup storedMuscleGroup;

    @Test
    void happyCaseTest() {
        FetchMuscleGroup fetchMuscleGroup = name -> new MuscleGroup(name, Exercises.of(new ArrayList<>()));

        StoreExercises storeExercises = (muscleGroupName, exercises) -> {
            storedMuscleGroup = new MuscleGroup(muscleGroupName, exercises);
            return exercises;
        };

        CreateExercisesController createExercisesController = new CreateExercisesController(fetchMuscleGroup, storeExercises);
        ExerciseNames input = new ExerciseNames();
        input.setInput("Bench Press, 2#Dumbbell Flys");

        ResponseEntity<ExercisesViewModel> exercises = createExercisesController.forMuscleGroup("Chest", input);

        Set<String> storedExerciseNames = storedMuscleGroup.getExercises().getExercises().stream().map(Exercise::getName).map(Name::toString).collect(toSet());

        assertEquals(Name.of("Chest"), storedMuscleGroup.getName());

        Exercise e1 = storedMuscleGroup.getExercises().getExercises().get(0);
        assertEquals(Name.of("Bench Press"), e1.getName());
        assertEquals(Multiplier.ONE, e1.getMultiplier());

        Exercise e2 = storedMuscleGroup.getExercises().getExercises().get(1);
        assertEquals(Name.of("Dumbbell Flys"), e2.getName());
        assertEquals(Multiplier.TWO, e2.getMultiplier());

        Set<String> viewModelExerciseNames = Objects.requireNonNull(exercises.getBody())
                .getExercises()
                .stream()
                .map(ExerciseViewModel::getName)
                .collect(toSet());

        assertEquals(2, storedExerciseNames.size());
        assertEquals(2, viewModelExerciseNames.size());

        assertTrue(viewModelExerciseNames.contains("Bench Press"));
        assertTrue(viewModelExerciseNames.contains("Dumbbell Flys"));

        viewModelExerciseNames
                .forEach(exerciseName -> assertTrue(storedExerciseNames.contains(exerciseName)));

    }
}
