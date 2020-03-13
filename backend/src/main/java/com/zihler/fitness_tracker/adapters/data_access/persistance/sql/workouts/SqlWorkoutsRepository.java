package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.inputs.WorkoutFromDBInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.outputs.WorkoutToDBOutput;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.Workouts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
@Profile("dev")
public class SqlWorkoutsRepository implements
        GenerateWorkoutId,
        FetchWorkout,
        FetchWorkouts,
        StoreWorkout,
        StoreSet {

    private JpaWorkoutsRepository workouts;
    private Random random;

    @Autowired
    public SqlWorkoutsRepository(JpaWorkoutsRepository workouts) {
        this.workouts = workouts;
        random = new Random();
    }

    @Override
    public Workout by(WorkoutId id) {
        var row = this.workouts.findByIdOrThrow(id.toString());

        var input = new WorkoutFromDBInput(row);

        return input.workout();
    }

    @Override
    public Workouts all() {
        var rows = this.workouts.findAll();

        var input = new WorkoutsFromDBInput(rows);

        return input.workouts();
    }

    @Override
    public WorkoutId next() {
        WorkoutId nextWorkoutId;
        do {
            nextWorkoutId = WorkoutId.of(random.nextInt() + "-" + random.nextInt()+"-"+ random.nextInt());
        } while (workouts.findById(nextWorkoutId.toString()).isEmpty());

        return nextWorkoutId;
    }

    @Override
    public Workout withValues(Workout workout) {
        var output = new WorkoutToDBOutput(workout);

        var row = output.row();

        var savedRow = this.workouts.save(row);

        var input = new WorkoutFromDBInput(savedRow);

        return input.workout();
    }

    @Override
    public Set withValues(WorkoutId workoutId, Name exerciseName, Set setToStore) {
        return null;
    }
}
