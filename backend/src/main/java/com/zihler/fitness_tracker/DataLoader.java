package com.zihler.fitness_tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading.WorkoutsJson;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.LoadingDataFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.JpaExerciseRepository;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.JpaMuscleGroupsRepository;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.JpaSetRepository;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.JpaWorkoutRepository;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs.WorkoutRowOutput;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

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
        Optional<WorkoutRow> workoutO = workoutRepository.findByWorkoutId(workoutsJson.getWorkouts().get(0).getWorkoutId());
        if (workoutO.isEmpty()) {
            workoutsJson.getWorkouts().forEach(
                    workoutJson -> {
                        var workoutRow = new WorkoutRowOutput(workoutJson).getRow();
                        workoutRepository.save(workoutRow);
                        var muscleGroups = workoutRow.getMuscleGroups();
                        muscleGroups.forEach(muscleGroupRow -> {
                            logger.info("Storing musclegroup row: " + muscleGroupRow);
                            if (muscleGroupsRepository.findByName(muscleGroupRow.getName()).isEmpty()) {
                                muscleGroupsRepository.save(muscleGroupRow);
                            }
                            var exercises = muscleGroupRow.getExercises();
                            exercises.forEach(exerciseRow -> {
                                if (exerciseRepository.findByName(muscleGroupRow.getName()).isEmpty()) {
                                    exerciseRepository.save(exerciseRow);
                                }
                                setRepository.saveAll(exerciseRow.getSets());
                            });
                        });
                    }
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

}
