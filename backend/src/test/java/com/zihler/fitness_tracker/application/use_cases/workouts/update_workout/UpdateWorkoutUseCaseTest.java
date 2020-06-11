package com.zihler.fitness_tracker.application.use_cases.workouts.update_workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.*;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.workouts.create_workout.CreateWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.workouts.create_workout.inbound_port.CreateWorkout;
import com.zihler.fitness_tracker.application.use_cases.workouts.update_workout.inbound_port.UpdateWorkout;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.zihler.fitness_tracker.domain.values.UnitOfMeasurement.KILOGRAMM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UpdateWorkoutUseCaseTest {
    @Test
    void test() {
        HashMap<WorkoutId, Workout> workouts = new HashMap<>();
        StoreWorkout storeWorkout = workout -> {
            workouts.put(workout.getWorkoutId(), workout);
            return workout;
        };

        FetchWorkout fetchWorkout = workouts::get;

        WorkoutDocument initialWorkout = initWorkout(storeWorkout);

        UpdateWorkout updateWorkoutUseCase = new UpdateWorkoutUseCase(fetchWorkout, storeWorkout);
        TestWorkoutPresenter output = new TestWorkoutPresenter();

        SetsDocument sets = new SetsDocument();
        sets.add(new SetDocument(Weight.of(50, KILOGRAMM), Repetitions.of(12), WaitingTime.of(45, UnitOfTime.SECONDS)));
        sets.add(new SetDocument(Weight.of(55, KILOGRAMM), Repetitions.of(12), WaitingTime.of(45, UnitOfTime.SECONDS)));
        MuscleGroupsDocument muscleGroups = new MuscleGroupsDocument(
                List.of(new MuscleGroupDocument(Name.of("Chest"))
                        .add(new ExerciseDocument(Name.of("Bench Press"), sets, Multiplier.ONE, true))));

        WorkoutDocument updatedWorkoutInput = new WorkoutDocument(initialWorkout.getWorkoutId(), initialWorkout.getCreationDate(), muscleGroups, false);
        updateWorkoutUseCase.invokeWith(updatedWorkoutInput, output);

        WorkoutDocument updatedWorkoutOutput = output.workoutDocument;
        Workout storedWorkout = fetchWorkout.by(updatedWorkoutOutput.getWorkoutId());

        assertIdEqualsBetween(updatedWorkoutInput, updatedWorkoutOutput, storedWorkout, initialWorkout);
        assertCreationTimeEqualsBetween(updatedWorkoutInput, updatedWorkoutOutput, storedWorkout, initialWorkout);

        assertEquals(0, initialWorkout.getMuscleGroups().count());
        assertEquals(1, updatedWorkoutInput.getMuscleGroups().count());
        assertEquals(1, updatedWorkoutOutput.getMuscleGroups().count());
        assertEquals(1, storedWorkout.getMuscleGroups().getMuscleGroups().size());

        List<ExerciseDocument> inputExercises = getExercisesIn(updatedWorkoutInput);
        assertEquals(1, inputExercises.size());
        assertEquals(Name.of("Bench Press"), inputExercises.get(0).getName());

        List<Exercise> storedExercises = getExercisesIn(storedWorkout);
        assertEquals(1, storedExercises.size());
        assertEquals(Name.of("Bench Press"), storedExercises.get(0).getName());

        List<ExerciseDocument> outputExercises = getExercisesIn(updatedWorkoutOutput);
        assertEquals(1, outputExercises.size());
        assertEquals(Name.of("Bench Press"), outputExercises.get(0).getName());

    }

    private List<Exercise> getExercisesIn(Workout storedWorkout) {
        return storedWorkout.getMuscleGroups()
                .getMuscleGroups()
                .stream()
                .map(MuscleGroup::getExercises)
                .map(Exercises::getExercises)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ExerciseDocument> getExercisesIn(WorkoutDocument updatedWorkoutOutput) {
        return updatedWorkoutOutput.getMuscleGroups()
                .getMuscleGroups()
                .stream()
                .map(MuscleGroupDocument::getExercises)
                .map(ExercisesDocument::getExercises)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private void assertCreationTimeEqualsBetween(WorkoutDocument updatedWorkoutInput, WorkoutDocument updatedWorkoutOutput, Workout storedWorkout, WorkoutDocument initialWorkout) {
        assertEquals(storedWorkout.getCreationDate(), updatedWorkoutInput.getCreationDate());
        assertEquals(storedWorkout.getCreationDate(), updatedWorkoutOutput.getCreationDate());
        assertEquals(storedWorkout.getCreationDate(), initialWorkout.getCreationDate());
    }

    private void assertIdEqualsBetween(WorkoutDocument updatedWorkoutInput, WorkoutDocument updatedWorkoutOutput, Workout storedWorkout, WorkoutDocument initialWorkout) {
        assertEquals(storedWorkout.getWorkoutId(), updatedWorkoutInput.getWorkoutId());
        assertEquals(storedWorkout.getWorkoutId(), updatedWorkoutOutput.getWorkoutId());
        assertEquals(storedWorkout.getWorkoutId(), initialWorkout.getWorkoutId());
    }

    private WorkoutDocument initWorkout(StoreWorkout repo) {
        CreateWorkout createWorkout = new CreateWorkoutUseCase(repo, () -> WorkoutId.of("987654L"));
        TestWorkoutPresenter output = new TestWorkoutPresenter();
        createWorkout.invokeWith(output);
        return output.workoutDocument;
    }

    private class TestWorkoutPresenter implements WorkoutPresenter {
        WorkoutDocument workoutDocument;

        @Override
        public void present(WorkoutDocument workoutDocument) {
            this.workoutDocument = workoutDocument;
        }
    }
}
