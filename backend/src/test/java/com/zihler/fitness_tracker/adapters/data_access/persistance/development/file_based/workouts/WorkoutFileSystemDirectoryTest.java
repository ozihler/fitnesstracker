package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WaitingTime;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static com.zihler.fitness_tracker.domain.values.MuscleGroup.of;
import static com.zihler.fitness_tracker.domain.values.MuscleGroups.muscleGroups;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutFileSystemDirectoryTest {
    @Test
    void test() {
        WorkoutFileSystemDirectory directory = WorkoutFileSystemDirectory.makeDirectory();

        Workout workout = new Workout(
                WorkoutId.of("x-1-2"),
                ZonedDateTime.now(),
                WorkoutTitle.of("WORKOUT TITLE"),
                muscleGroups(
                        of("Chest",
                                Exercise.of("Bench Press",
                                        Sets.of(List.of(
                                                Set.of(Weight.of(55, UnitOfMeasurement.KILOGRAMM), Repetitions.of(12), WaitingTime.of(45, UnitOfTime.SECONDS)),
                                                Set.of(Weight.of(50, UnitOfMeasurement.KILOGRAMM), Repetitions.of(12), WaitingTime.of(45, UnitOfTime.SECONDS)),
                                                Set.of(Weight.of(45, UnitOfMeasurement.KILOGRAMM), Repetitions.of(12), WaitingTime.of(45, UnitOfTime.SECONDS))
                                                )
                                        )))));


        directory.save(workout);

        Workouts workouts = directory.fetch();
        List<Workout> workouts1 = workouts.getWorkouts();
        assertEquals(1, workouts1.size());
        Workout workout1 = workouts1.get(0);
        assertEquals(workout.getWorkoutId(), workout1.getWorkoutId());
    }

}