package com.zihler.fitness_tracker.application.use_cases.update_workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.*;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercise;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.create_workout.CreateWorkoutUseCase;
import com.zihler.fitness_tracker.application.use_cases.create_workout.inbound_port.CreateWorkout;
import com.zihler.fitness_tracker.application.use_cases.update_workout.inbound_port.UpdateWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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

        FetchMuscleGroup fetchMuscleGroup = MuscleGroup::new;
        FetchExercise fetchExercise = e -> new Exercise(null, e);

        WorkoutDocument initialWorkout = initWorkout(storeWorkout);

        UpdateWorkout updateWorkoutUseCase = new UpdateWorkoutUseCase(fetchWorkout, storeWorkout);
        TestWorkoutPresenter output = new TestWorkoutPresenter();

        MuscleGroupsDocument muscleGroups = new MuscleGroupsDocument(
                Set.of(new MuscleGroupDocument(Name.of("Chest"))
                        .add(new ExerciseDocument(Name.of("Bench Press"))
                                .add(new SetDocument(Weight.of(50, KILOGRAMM), Repetitions.of(12), WaitingTime.of(45, UnitOfTime.SECONDS)))
                                .add(new SetDocument(Weight.of(55, KILOGRAMM), Repetitions.of(12), WaitingTime.of(45, UnitOfTime.SECONDS))))));

        WorkoutDocument updatedWorkoutInput = new WorkoutDocument(initialWorkout.getWorkoutId(), initialWorkout.getCreationDateTime(), WorkoutTitle.of("New Title"), muscleGroups);
        updateWorkoutUseCase.invokeWith(updatedWorkoutInput, output);

        WorkoutDocument updatedWorkoutOutput = output.workoutDocument;
        Workout storedWorkout = fetchWorkout.by(updatedWorkoutOutput.getWorkoutId());

        assertIdEqualsBetween(updatedWorkoutInput, updatedWorkoutOutput, storedWorkout, initialWorkout);
        assertCreationTimeEqualsBetween(updatedWorkoutInput, updatedWorkoutOutput, storedWorkout, initialWorkout);
        assertTitleEqualsBetween(updatedWorkoutInput, updatedWorkoutOutput, storedWorkout);
        assertTitleNotEqualBetween(initialWorkout, updatedWorkoutInput, updatedWorkoutOutput, storedWorkout);

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

    private void assertTitleNotEqualBetween(WorkoutDocument initialWorkout, WorkoutDocument updatedWorkoutInput, WorkoutDocument updatedWorkoutOutput, Workout storedWorkout) {
        assertNotEquals(storedWorkout.getWorkoutTitle(), initialWorkout.getWorkoutTitle());
        assertNotEquals(updatedWorkoutOutput.getWorkoutTitle(), initialWorkout.getWorkoutTitle());
        assertNotEquals(updatedWorkoutInput.getWorkoutTitle(), initialWorkout.getWorkoutTitle());
    }

    private void assertTitleEqualsBetween(WorkoutDocument updatedWorkoutInput, WorkoutDocument updatedWorkoutOutput, Workout storedWorkout) {
        assertEquals(storedWorkout.getWorkoutTitle(), updatedWorkoutInput.getWorkoutTitle());
        assertEquals(storedWorkout.getWorkoutTitle(), updatedWorkoutOutput.getWorkoutTitle());
    }

    private void assertCreationTimeEqualsBetween(WorkoutDocument updatedWorkoutInput, WorkoutDocument updatedWorkoutOutput, Workout storedWorkout, WorkoutDocument initialWorkout) {
        assertEquals(storedWorkout.getCreationDateTime(), updatedWorkoutInput.getCreationDateTime());
        assertEquals(storedWorkout.getCreationDateTime(), updatedWorkoutOutput.getCreationDateTime());
        assertEquals(storedWorkout.getCreationDateTime(), initialWorkout.getCreationDateTime());
    }

    private void assertIdEqualsBetween(WorkoutDocument updatedWorkoutInput, WorkoutDocument updatedWorkoutOutput, Workout storedWorkout, WorkoutDocument initialWorkout) {
        assertEquals(storedWorkout.getWorkoutId(), updatedWorkoutInput.getWorkoutId());
        assertEquals(storedWorkout.getWorkoutId(), updatedWorkoutOutput.getWorkoutId());
        assertEquals(storedWorkout.getWorkoutId(), initialWorkout.getWorkoutId());
    }

    private WorkoutDocument initWorkout(StoreWorkout repo) {
        CreateWorkout createWorkout = new CreateWorkoutUseCase(repo);
        TestWorkoutPresenter output = new TestWorkoutPresenter();
        createWorkout.invokeWith(WorkoutTitle.of("Start Title"), output);
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