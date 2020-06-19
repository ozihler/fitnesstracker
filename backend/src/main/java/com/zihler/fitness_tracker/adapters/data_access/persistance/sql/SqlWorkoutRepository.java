package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.exceptions.ExerciseNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs.WorkoutFromSqlInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs.WorkoutsFromSqlInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs.SetToSqlOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs.WorkoutToSqlOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.Workouts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("prod")
public class SqlWorkoutRepository implements
        GenerateWorkoutId,
        FetchWorkout,
        FetchWorkouts,
        StoreWorkout,
        AddSetToWorkout {

    private final JpaWorkoutRepository workoutRepository;

    @Autowired
    public SqlWorkoutRepository(JpaWorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    private static int currentId = 1;
    private final int userId = 1;

    @Override
    public Workout by(WorkoutId id) {
        WorkoutRow row = workoutRepository.findByWorkoutIdOrThrow(id.toString());
        return new WorkoutFromSqlInput(row).getWorkout();
    }

    @Override
    public Workouts all() {
        var allWorkoutRows = workoutRepository.findAll();
        return new WorkoutsFromSqlInput(allWorkoutRows).getWorkouts();
    }

    @Override
    public WorkoutId next() {
        String nextWorkoutId;
        do {
            nextWorkoutId = String.format("%d-%d", userId, currentId++);
        } while (workoutRepository.findByWorkoutId(nextWorkoutId).isPresent());

        return WorkoutId.of(nextWorkoutId);
    }

    @Override
    public Set withValues(WorkoutId workoutId, Name exerciseName, Set setToStore) {
        var workout = workoutRepository.findByWorkoutIdOrThrow(workoutId.toString());
        var row = new SetToSqlOutput(setToStore).getRow();
        workout.getMuscleGroups()
                .stream()
                .map(MuscleGroupRow::getExercises)
                .flatMap(Collection::stream)
                .filter(e -> e.getName().equalsIgnoreCase(exerciseName.toString()))
                .findFirst()
                .orElseThrow(() -> new ExerciseNotFoundException("Could not find Exercise with name " + exerciseName + " in workout " + workoutId + " to add set"))
                .getSets()
                .add(row);

        this.workoutRepository.save(workout);

        return setToStore;
    }

    @Override
    public Workout withValues(Workout workout) {
        var workoutToSaveRow = new WorkoutToSqlOutput(workout).getRow();
        var storedWorkoutRow = this.workoutRepository.save(workoutToSaveRow);

        return new WorkoutFromSqlInput(storedWorkoutRow)
                .getWorkout();
    }
}
