package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.LoadingDataFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.SqlMuscleGroupsExercisesRepository;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.SqlWorkoutRepository;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Workouts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private final SqlWorkoutRepository workoutRepository;
    private final SqlMuscleGroupsExercisesRepository muscleGroupsExercisesRepository;


    @Autowired
    public DataLoader(SqlWorkoutRepository workoutRepository,
                      SqlMuscleGroupsExercisesRepository muscleGroupsExercisesRepository) {
        this.workoutRepository = workoutRepository;
        this.muscleGroupsExercisesRepository = muscleGroupsExercisesRepository;
    }

    public void run(ApplicationArguments args) {
        WorkoutsJson workoutsJson = loadExistingWorkouts();
        Workouts workouts = new WorkoutsFromJsonInput(workoutsJson).getWorkouts();

        storeMuscleGroupsFoundIn(workouts);
        storeExercisesOfMuscleGroupsFoundIn(workouts);
        store(workouts);
    }

    private void store(Workouts workouts) {
        workouts.getWorkouts().forEach(workoutRepository::withValues);
    }

    private void storeMuscleGroupsFoundIn(Workouts workouts) {
        workouts.getWorkouts()
                .stream()
                .map(Workout::getMuscleGroups)
                .forEach(muscleGroupsExercisesRepository::withValues);
    }

    private void storeExercisesOfMuscleGroupsFoundIn(Workouts workouts) {
        workouts.getWorkouts()
                .stream()
                .map(Workout::getMuscleGroups)
                .map(MuscleGroups::getMuscleGroups)
                .flatMap(Collection::stream)
                .forEach(muscleGroup -> muscleGroupsExercisesRepository.ofMuscleGroup(muscleGroup.getName(), muscleGroup.getExercises()));
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

}
