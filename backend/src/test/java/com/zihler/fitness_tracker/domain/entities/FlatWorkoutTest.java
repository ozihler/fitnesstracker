package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WaitingTime;
import com.zihler.fitness_tracker.domain.values.*;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlatWorkoutTest {

    @Test
    void createOrderedSets() {
        FlatWorkout flatWorkout = new FlatWorkout(WorkoutId.of(1), ZonedDateTime.now(), WorkoutTitle.of("Title"));
        flatWorkout.add(FlatSet.of(flatWorkout.getWorkoutId(), MuscleGroupName.of("Chest"), ExerciseName.of("Bench Press"), Weight.of(50), Repetitions.of(12), WaitingTime.of(45)));
        flatWorkout.add(FlatSet.of(flatWorkout.getWorkoutId(), MuscleGroupName.of("Chest"), ExerciseName.of("Flying"), Weight.of(25), Repetitions.of(12), WaitingTime.of(45)));
        flatWorkout.add(FlatSet.of(flatWorkout.getWorkoutId(), MuscleGroupName.of("Chest"), ExerciseName.of("Bench Press"), Weight.of(40), Repetitions.of(12), WaitingTime.of(45)));
        flatWorkout.add(FlatSet.of(flatWorkout.getWorkoutId(), MuscleGroupName.of("Chest"), ExerciseName.of("Flying"), Weight.of(20), Repetitions.of(12), WaitingTime.of(45)));
        flatWorkout.add(FlatSet.of(flatWorkout.getWorkoutId(), MuscleGroupName.of("Chest"), ExerciseName.of("Bench Press"), Weight.of(40), Repetitions.of(12), WaitingTime.of(45)));
        flatWorkout.add(FlatSet.of(flatWorkout.getWorkoutId(), MuscleGroupName.of("Chest"), ExerciseName.of("Dumbbell Bench Press"), Weight.of(24), Repetitions.of(12), WaitingTime.of(45)));
        flatWorkout.add(FlatSet.of(flatWorkout.getWorkoutId(), MuscleGroupName.of("Chest"), ExerciseName.of("Flying"), Weight.of(20), Repetitions.of(12), WaitingTime.of(45)));
        flatWorkout.add(FlatSet.of(flatWorkout.getWorkoutId(), MuscleGroupName.of("Chest"), ExerciseName.of("Dumbbell Bench Press"), Weight.of(22), Repetitions.of(12), WaitingTime.of(45)));
        flatWorkout.add(FlatSet.of(flatWorkout.getWorkoutId(), MuscleGroupName.of("Chest"), ExerciseName.of("Dumbbell Bench Press"), Weight.of(20), Repetitions.of(12), WaitingTime.of(45)));
        flatWorkout.add(FlatSet.of(flatWorkout.getWorkoutId(), MuscleGroupName.of("Chest"), ExerciseName.of("Bench Press"), Weight.of(40), Repetitions.of(12), WaitingTime.of(45)));

        Set<MuscleGroupName> muscleGroupNames = flatWorkout.getMuscleGroupNames();
        assertEquals(1, muscleGroupNames.size());
        assertTrue(muscleGroupNames.contains(MuscleGroupName.of("Chest")));

        Set<ExerciseName> exerciseNames = flatWorkout.getExerciseNames();
        assertEquals(3, exerciseNames.size());
        assertTrue(exerciseNames.contains(ExerciseName.of("Bench Press")));
        assertTrue(exerciseNames.contains(ExerciseName.of("Flying")));
        assertTrue(exerciseNames.contains(ExerciseName.of("Dumbbell Bench Press")));

    }
}
