package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts;

import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.zihler.fitness_tracker.domain.values.MuscleGroups.muscleGroups;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutFileSystemDirectoryTest {
    @Test
    void test() {
        WorkoutFileSystemDirectory directory = WorkoutFileSystemDirectory.mkDir("test-workouts");

        final Exercise[] bench_presses = new Exercise[]{
                new Exercise(
                        Name.of("Bench Press"),
                        Sets.of(List.of(
                                Set.withValues(Weight.of(55, UnitOfMeasurement.KILOGRAMM), Repetitions.of(12), WaitingTime.of(45, UnitOfTime.SECONDS)),
                                Set.withValues(Weight.of(50, UnitOfMeasurement.KILOGRAMM), Repetitions.of(12), WaitingTime.of(45, UnitOfTime.SECONDS)),
                                Set.withValues(Weight.of(45, UnitOfMeasurement.KILOGRAMM), Repetitions.of(12), WaitingTime.of(45, UnitOfTime.SECONDS))
                        )),
                        Multiplier.ONE)
        };

        Workout workout = new Workout(WorkoutId.of("x-1-2"), CreationDate.now(), muscleGroups(
                new MuscleGroup(
                        Name.of("Chest"),
                        Exercises.of(bench_presses))));


        directory.save(workout);

        Workouts workouts = directory.fetch();
        List<Workout> workouts1 = workouts.getWorkouts();
        assertEquals(1, workouts1.size());
        Workout workout1 = workouts1.get(0);
        assertEquals(workout.getWorkoutId(), workout1.getWorkoutId());
        directory.deleteFolder();
    }

}
