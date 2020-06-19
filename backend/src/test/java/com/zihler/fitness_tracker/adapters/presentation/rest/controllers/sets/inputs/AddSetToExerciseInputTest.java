package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.exceptions.IllegalSetDetailsException;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.domain.exceptions.IllegalNameException;
import com.zihler.fitness_tracker.domain.exceptions.WorkoutIdNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("AddSetToExercisesInput")
class AddSetToExerciseInputTest {

    @Test
    @DisplayName("does not throw exception when waiting time is not set")
    void doesNotThrowExceptionIfWaitingTimeIsNotSet() {
        var expectedSetToAdd = SetViewModel.of(50, 12, 0);
        var input = new AddSetToExerciseInput("1-1", "Chest", expectedSetToAdd);
        var setToAdd = input.set();
        assertEquals(expectedSetToAdd.getWeight(), setToAdd.getWeight().value());
        assertEquals(expectedSetToAdd.getWeightUnit(), setToAdd.getWeight().unitOfMeasurement().shortname());
        assertEquals(expectedSetToAdd.getNumberOfRepetitions(), setToAdd.getRepetitions().number());
        assertEquals(expectedSetToAdd.getWaitingTime(), setToAdd.getWaitingTime().value());
        assertEquals(expectedSetToAdd.getWaitingTimeUnit(), setToAdd.getWaitingTime().unitOfTime().shortname());
    }

    @Test
    @DisplayName("throws exception when set weight is smaller than 0")
    void throwsExceptionWhenSetWeightIsSmaller0() {
        var input = new AddSetToExerciseInput("1-1", "Chest",
                SetViewModel.of(-1, 12, 45)
        );

        assertThrows(IllegalSetDetailsException.class, input::set);
    }

    @Test
    @DisplayName("throws exception when set weight is 0")
    void throwsExceptionWhenSetWeightIs0() {
        var input = new AddSetToExerciseInput("1-1", "Chest",
                SetViewModel.of(0, 12, 45)
        );

        assertThrows(IllegalSetDetailsException.class, input::set);
    }

    @Test
    @DisplayName("throws exception when set number of repetitions is 0")
    void throwsExceptionWhenSetNumberOfRepetitionsIs0() {
        var input = new AddSetToExerciseInput("1-1", "Chest",
                SetViewModel.of(50, 0, 45)
        );

        assertThrows(IllegalSetDetailsException.class, input::set);
    }

    @Test
    @DisplayName("throws exception when set number of repetitions is smaller than 0")
    void throwsExceptionWhenSetNumberOfRepetitionsIsSmallerThan0() {
        var input = new AddSetToExerciseInput("1-1", "Chest",
                SetViewModel.of(50, -1, 45)
        );

        assertThrows(IllegalSetDetailsException.class, input::set);
    }

    @Test
    @DisplayName("throws exception when workout id is invalid")
    void throwsExceptionWhenWorkoutIdIsInvalid() {
        var input = new AddSetToExerciseInput(null, null, null);

        assertThrows(WorkoutIdNotFoundException.class, input::workoutId);

        var input2 = new AddSetToExerciseInput("", null, null);

        assertThrows(WorkoutIdNotFoundException.class, input2::workoutId);
    }

    @Test
    @DisplayName("throws exception when exercise name is invalid")
    void throwsExceptionWhenExerciseNameIsInvalid() {
        var input = new AddSetToExerciseInput(null, null, null);

        assertThrows(IllegalNameException.class, input::exerciseName);

        var input2 = new AddSetToExerciseInput(null, "", null);

        assertThrows(IllegalNameException.class, input2::exerciseName);
    }

    @Test
    @DisplayName("throws exception when set to add is null")
    void throwsExceptionWhenSetToAddIsNull() {
        var input = new AddSetToExerciseInput(null, null, null);

        assertThrows(IllegalSetDetailsException.class, input::set);
    }

}
