package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.InMemoryWorkoutRepository;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;
import org.junit.jupiter.api.Test;

import static com.zihler.fitness_tracker.domain.values.MuscleGroups.muscleGroups;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddSetToExercisesControllerTest {
    @Test
    void canAddSetToExistingExercise() {


        var workoutId = "1-1";
        var exercise = "Bench Press";

        InMemoryWorkoutRepository repo = new InMemoryWorkoutRepository();
        var existingWorkout = new Workout(WorkoutId.of(workoutId), CreationDate.now(), WorkoutTitle.of("W Chest"), muscleGroups(
                new MuscleGroup(
                        Name.of("Chest"),
                        Exercises.of(
                                new Exercise(
                                        Name.of("Bench Press"),
                                        Sets.empty(),
                                        Multiplier.ONE)))));

        repo.withValues(existingWorkout);

        var controller = new AddSetToExercisesController(repo);
        var setToAdd = new SetViewModel(
                50,
                "kg",
                12,
                45,
                "s"
        );

        controller.addSetToExercise(workoutId, exercise, setToAdd);

        Workout workout = repo.by(WorkoutId.of(workoutId));
        Set set = workout.getMuscleGroups().getMuscleGroups().get(0).getExercises().getExercises().get(0).getSets().getSets().get(0);

        assertEquals(setToAdd.getWeight(), set.getWeight().value());
        assertEquals(setToAdd.getWeightUnit(), set.getWeight().unitOfMeasurement().shortname());
        assertEquals(setToAdd.getNumberOfRepetitions(), set.getRepetitions().number());
        assertEquals(setToAdd.getWaitingTime(), set.getWaitingTime().value());
        assertEquals(setToAdd.getWaitingTimeUnit(), set.getWaitingTime().unitOfTime().shortname());
    }

}
