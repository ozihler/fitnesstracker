package com.zihler.fitness_tracker.adapters.data_access.persistance.in_memory;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Profile("prod")
public class DevelopmentWorkoutRepository
        implements
        GenerateWorkoutId,
        FetchWorkout,
        FetchWorkouts,
        StoreWorkout,
        StoreSet {

    private static int currentId = 1;
    private int userId = 1;

    private Map<WorkoutId, Workout> workouts = new HashMap<>();

    @Override
    public Workout withValues(Workout workout) {
        workouts.put(workout.getWorkoutId(), workout);
        return workout;
    }

    @Override
    public Workout by(WorkoutId id) {
        return workouts.get(id);
    }

    @Override
    public Workouts all() {
        return new Workouts(new ArrayList<>(workouts.values()));
    }

    @Override
    public WorkoutId next() {
        WorkoutId nextWorkoutId;
        do {
            nextWorkoutId = WorkoutId.of(String.format("WORKOUT-%d-%d", userId, currentId++));
        } while (workouts.containsKey(nextWorkoutId));

        return nextWorkoutId;
    }

    @Override
    public Set withValues(WorkoutId workoutId, Name exerciseName, Set setToStore) {
        Workout workout = workouts.get(workoutId);
        workout.getMuscleGroups().getMuscleGroups()
                .stream()
                .map(MuscleGroup::getExercises)
                .map(Exercises::getExercises)
                .flatMap(Collection::stream)
                .filter(e -> e.getName().equals(exerciseName))
                .findFirst().get()
                .getSets()
                .add(setToStore);
        return setToStore;
    }

}
