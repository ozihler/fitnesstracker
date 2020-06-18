package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.exceptions.IllegalWorkoutIdException;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.Workouts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("prod")
public class SqlWorkoutRepository implements
        GenerateWorkoutId,
        FetchWorkout,
        FetchWorkouts,
        StoreWorkout,
        StoreSet {

    private final JpaWorkoutRepository workoutRepository;
    @Autowired
    public SqlWorkoutRepository(JpaWorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;

    }

    @Override
    public Workout by(WorkoutId id) {
        Optional<WorkoutRow> optional = workoutRepository.findByWorkoutId(id.toString());
        if (optional.isEmpty()) {
            throw new IllegalWorkoutIdException("could not find workout with id " + id.toString());
        }
        // optional.map(row -> row.);
        return null;
    }

    @Override
    public Workouts all() {
        return null;
    }

    @Override
    public WorkoutId next() {
        return null;
    }

    @Override
    public Set withValues(WorkoutId workoutId, Name exerciseName, Set setToStore) {
        return null;
    }

    @Override
    public Workout withValues(Workout workout) {
        return null;
    }
}
