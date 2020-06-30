package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.exceptions.ExerciseNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs.WorkoutFromSqlInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.inputs.WorkoutsFromSqlInput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs.SetToSqlOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs.WorkoutToSqlOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.*;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.Workouts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("prod")
public class SqlWorkoutRepository implements
        GenerateWorkoutId,
        FetchWorkout,
        FetchWorkouts,
        StoreWorkout,
        AddSetToWorkout {

    private final JpaWorkoutRepository workoutRepository;
    private final JpaSetRepository setRepository;
    private final JpaExerciseRepository exerciseRepository;
    private final SqlMuscleGroupsExercisesRepository muscleGroupsExercisesRepository;

    @Autowired
    public SqlWorkoutRepository(JpaWorkoutRepository workoutRepository,
                                JpaSetRepository setRepository,
                                JpaExerciseRepository exerciseRepository,
                                SqlMuscleGroupsExercisesRepository muscleGroupsExercisesRepository) {

        this.workoutRepository = workoutRepository;
        this.setRepository = setRepository;
        this.exerciseRepository = exerciseRepository;
        this.muscleGroupsExercisesRepository = muscleGroupsExercisesRepository;
    }

    private static int currentId = 1;
    private final int userId = 1;

    @Override
    public Workout by(WorkoutId id) {
        WorkoutRow row = workoutRepository.findByWorkoutIdOrThrow(id.toString());
        var allSets = setRepository.findAllByWorkout(row);
        //todo append all muscle groups, exercises, and sets here....
        return new WorkoutFromSqlInput(row, allSets).getWorkout();
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
        var exerciseRow = exerciseRepository.findByNameOrThrow(exerciseName.toString());
        var row = new SetToSqlOutput(setToStore, exerciseRow, workout).getRow();
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
        assureAllMuscleGroupsExistIn(workout); // assure the muscle group exists
        assureAllExercisesOfMuscleGroupsExistIn(workout);
        var storedWorkoutRow = this.workoutRepository.save(workoutToSaveRow);
        storeAllSetsOf(workout);

        return new WorkoutFromSqlInput(storedWorkoutRow, allSets)
                .getWorkout();
    }

    private void storeAllSetsOf(Workout workout) {
        var workoutRow = workoutRepository.findByWorkoutIdOrThrow(workout.getWorkoutId().toString());
        for (MuscleGroup muscleGroup : workout.getMuscleGroups().getMuscleGroups()) {
            Exercises exercises = muscleGroup.getExercises();
            List<Exercise> exercisesExercises = exercises.getExercises();
            for (Exercise exercise : exercisesExercises) {
                ExerciseRow exerciseRow = getExerciseRow(muscleGroup, exercise);

                var setRowsToStore = exercise.getSets().getSets()
                        .stream()
                        .map(set -> new SetToSqlOutput(set, exerciseRow, workoutRow))
                        .map(SetToSqlOutput::getRow)
                        .collect(Collectors.toList());
                setRepository.saveAll(setRowsToStore);
            }
        }

    }

    private ExerciseRow getExerciseRow(MuscleGroup muscleGroup, Exercise exercise) {
        var exerciseRowOptional = exerciseRepository.findByName(exercise.getName().toString());
        if (exerciseRowOptional.isEmpty()) {
            muscleGroupsExercisesRepository.ofMuscleGroup(muscleGroup.getName(), Exercises.of(exercise));
            return exerciseRepository.findByNameOrThrow(exercise.getName().toString());
        } else {
            return exerciseRowOptional.get();
        }
    }

    private void assureAllExercisesOfMuscleGroupsExistIn(Workout workout) {
        workout.getMuscleGroups()
                .getMuscleGroups()
                .forEach(muscleGroup -> muscleGroupsExercisesRepository.ofMuscleGroup(muscleGroup.getName(), muscleGroup.getExercises()));
    }

    private void assureAllMuscleGroupsExistIn(Workout workout) {
        muscleGroupsExercisesRepository.withValues(workout.getMuscleGroups());
    }
}
