package com.zihler.fitness_tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading.WorkoutsJson;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.LoadingDataFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.JpaExerciseRepository;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.JpaMuscleGroupsRepository;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.JpaSetRepository;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.JpaWorkoutRepository;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs.WorkoutRowOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataLoader implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private final JpaWorkoutRepository workoutRepository;
    private final JpaMuscleGroupsRepository muscleGroupsRepository;
    private final JpaExerciseRepository exerciseRepository;
    private final JpaSetRepository setRepository;

    @Autowired
    public DataLoader(JpaWorkoutRepository workoutRepository,
                      JpaMuscleGroupsRepository muscleGroupsRepository,
                      JpaExerciseRepository exerciseRepository,
                      JpaSetRepository setRepository
    ) {
        this.workoutRepository = workoutRepository;
        this.muscleGroupsRepository = muscleGroupsRepository;
        this.exerciseRepository = exerciseRepository;
        this.setRepository = setRepository;
    }

    public void run(ApplicationArguments args) {

        var workoutsJson = loadExistingWorkouts();
        workoutsJson.getWorkouts().forEach(workoutJson -> {
            new WorkoutRowOutput(workoutJson).getRow().getMuscleGroups().stream().filter(muscleGroup -> muscleGroupsRepository.findByName(muscleGroup.getName()).isEmpty()).forEach(muscleGroupsRepository::save);
        });
        workoutsJson.getWorkouts().forEach(workoutJson -> {
            new WorkoutRowOutput(workoutJson).getRow().getMuscleGroups().stream().filter(muscleGroup -> muscleGroupsRepository.findByName(muscleGroup.getName()).isPresent()).forEach(muscleGroupRow -> muscleGroupRow.getExercises().stream().filter(exerciseRow -> exerciseRepository.findByName(exerciseRow.getName()).isEmpty()).forEach(exerciseRepository::save));
        });
        workoutsJson.getWorkouts().forEach(workoutJson -> {
            // todo: fetch stored workout/muscle group/exercise/ set individually (because each has an own id) ==> Implement Adapters and call adapter methods!
            new WorkoutRowOutput(workoutJson).getRow().getMuscleGroups().forEach(muscleGroupRow -> {
                muscleGroupRow.getExercises().stream().map(ExerciseRow::getSets).forEach(entities -> {
                    entities.forEach(entity -> {
                        logger.info("Exercise is stored? : " + exerciseRepository.findByName(entity.getExercise().getName()).isPresent());
                        logger.info("Storing " + entity.getId() + " of exercise " + entity.getExercise().getName());
                        setRepository.save(entity);
                    });
                });
            });
        });
        workoutsJson.getWorkouts().stream()
                .filter(workoutJson -> workoutRepository.findByWorkoutId(workoutJson.getWorkoutId()).isEmpty()).forEach(workoutJson -> {
            workoutRepository.save(new WorkoutRowOutput(workoutJson).getRow());
        });
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
