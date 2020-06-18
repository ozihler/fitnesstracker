package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading.WorkoutsJson;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.LoadingDataFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs.WorkoutRowOutput;
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

import java.io.IOException;
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

        var workoutsJson = loadExistingWorkouts();
        Optional<WorkoutRow> workoutO = workoutRepository.findByWorkoutId(workoutsJson.getWorkouts().get(0).getWorkoutId());
        if (workoutO.isEmpty()) {
            workoutsJson.getWorkouts().forEach(
                    workoutJson -> workoutRepository.save(new WorkoutRowOutput(workoutJson).getRow())
            );
        }
    }

    private WorkoutsJson loadExistingWorkouts() {
        try {
            return new ObjectMapper().readValue(
                    this.getClass().getResource("/data/json/workouts_until_18_06_2020.json"),
                    WorkoutsJson.class);
        } catch (IOException e) {
            throw new LoadingDataFromFileSystemFailed(e);
        }

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
